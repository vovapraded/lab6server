package org.example.connection;

import lombok.Getter;
import com.google.common.primitives.Bytes;
import org.common.commands.Command;
import org.common.utility.Console;
import org.common.utility.InvalidFormatException;
import org.example.managers.ExecutorOfCommands;
import org.common.utility.PropertyUtil;
import org.example.utility.RecieveDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.example.utility.*;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.apache.commons.lang3.ArrayUtils.toPrimitive;

public class UdpServer implements ResponseListener {

    private final InetSocketAddress serverAddress;
    private final ExecutorOfCommands executor;
    private final ByteBuffer buffer = ByteBuffer.allocate(1024);


    private  InetSocketAddress clientAddress;
    private final int PACKET_SIZE = 1024;
    private final int DATA_SIZE = PACKET_SIZE - 1;

    private static final Logger logger = LoggerFactory.getLogger(UdpServer.class);

    private DatagramSocket datagramSocket;
    private boolean running=true;
    public UdpServer(ExecutorOfCommands executor)  {
        this.executor =executor;
        this.serverAddress = new InetSocketAddress(PropertyUtil.getAddress(),PropertyUtil.getPort());
        openNewSocket();
        logger.debug("Открыт сокет");
    }


    private void openNewSocket(){
        try {
            this.datagramSocket = new DatagramSocket(serverAddress);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() throws RecieveDataException, SocketException {
        while (running) {
                var commandFromClient = receiveData();
                executor.executeCommand(commandFromClient);
        }
    }
    public void connectToClient(SocketAddress addr) throws SocketException {
        datagramSocket.setSoTimeout(10000);
        datagramSocket.connect(addr);
    }

    public void disconnectFromClient() throws SocketException {
        datagramSocket.disconnect();
        datagramSocket.setSoTimeout(0);

    }



    public Command receiveData() throws RecieveDataException, SocketException {
        var received = false;
        var result = new byte[0];
        SocketAddress addr = null;
        int i = 1;
        while (!received){
            var data = new byte[PACKET_SIZE];
            var dp = new DatagramPacket(data, PACKET_SIZE);
            try {
                datagramSocket.receive(dp);
            } catch (SocketTimeoutException e){
                    disconnectFromClient();
                    throw new RecieveDataException("Клиент " + addr + " не досылал пакеты более 10 секунд, клиент отключен");

            } catch (IOException e) {
                disconnectFromClient();
                throw new RecieveDataException("Не удалось получить данные, адрес прошлого обслуженного клиента: " + clientAddress);
            }
            addr = dp.getSocketAddress();
            if (i==1) connectToClient(addr);

            if ((data[data.length - 1] == 3 || data[data.length - 1] == 1) && result.length != 0){
                result = new byte[0];
                i = 1;
                logger.debug("Получен "+dp+" пакет "+i+" нового запроса, с адреса "+addr+", старый запрос с адреса "+clientAddress+" был не корректен");
            }
            clientAddress = (InetSocketAddress) addr;

            if (data[data.length - 1] == 2 || data[data.length - 1] == 3) {
                received = true;

                logger.debug("Получен "+dp+" последний пакет запроса с адреса "+addr+" - пакет "+ i);
            }
            else {
                logger.debug("Получен "+dp+" пакет "+i+" с адреса "+addr);
            }
            result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
            i+=1;
        }
        disconnectFromClient();

        Command command = Deserializer.deserialize(result);
        logger.debug("Команда "+command.getClass().getName()+" десериализованна успешно");

        return command;
    }

    private void sendData(byte[] data) throws IOException {
        byte[][] packets=new byte[(int)Math.ceil(data.length / (double)DATA_SIZE)][PACKET_SIZE];
        for (int i = 0; i<packets.length;i++){

            if (i == packets.length - 1) {
                packets[i] = Bytes.concat(Arrays.copyOfRange(data,i*DATA_SIZE,(i+1)*DATA_SIZE), new byte[]{1});
            } else {
                packets[i] = Bytes.concat(Arrays.copyOfRange(data,i*DATA_SIZE,(i+1)*DATA_SIZE), new byte[]{0});
            }
        }

        for (byte[] packet : packets) {
            var dp= new DatagramPacket(packet,packet.length,clientAddress);
            datagramSocket.send(dp);

        }
    }
    public void sendResponse(byte[] data) {
        try {
            if (clientAddress != null) {
                sendData(data);
            } else {
                logger.error("No client address available.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(byte[] data) {
        sendResponse(data);
    }
}
