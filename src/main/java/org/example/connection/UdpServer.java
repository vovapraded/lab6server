package org.example.connection;

import lombok.Getter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import com.google.common.primitives.Bytes;
import org.example.commands.Command;
import org.example.managers.ExecutorOfCommands;
import org.example.utility.Deserializer;
import org.example.utility.PropertyUtil;
import org.example.utility.RecieveDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.apache.commons.lang3.ArrayUtils.toPrimitive;

public class UdpServer {

    private final InetSocketAddress serverAddress;
    private final ExecutorOfCommands executor= new ExecutorOfCommands();
    private final ByteBuffer buffer = ByteBuffer.allocate(1024);


    private  InetSocketAddress clientAddress;
    private final int PACKET_SIZE = 1024;
    private final int DATA_SIZE = PACKET_SIZE - 1;

    private static final Logger logger = LoggerFactory.getLogger(UdpServer.class);

    private DatagramSocket datagramSocket;
    private boolean running=true;
    @Getter
    private static UdpServer Instance = new UdpServer();

    private UdpServer()  {
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
    public void run() throws RecieveDataException{
        while (running) {
            var commandFromClient = receiveData();
            executor.executeCommand(commandFromClient);


        }
    }
    public void connectToClient(SocketAddress addr) throws SocketException {
        datagramSocket.connect(addr);
    }

    public void disconnectFromClient() {
        datagramSocket.disconnect();
    }



    public Command receiveData() throws RecieveDataException {
        var received = false;
        var result = new byte[0];
        SocketAddress addr = null;
        int i = 1;
        while (!received){
            var data = new byte[PACKET_SIZE];
            var dp = new DatagramPacket(data, PACKET_SIZE);
            try {
                datagramSocket.receive(dp);
                System.out.println(dp);
            } catch (IOException e) {
                throw new RecieveDataException("Не удалось получить данные, адрес прошлого обслуженного клиента: "+clientAddress);
            }
            addr = dp.getSocketAddress();
            if ((data[data.length - 1] == 3 || data[data.length - 1] == 1) && result.length != 0){
                result = new byte[0];
                i = 1;
                logger.debug("Получен первый пакет "+i+" нового запроса, с адреса "+addr+", старый запрос с адреса "+clientAddress+" был не корректен");
            }
            else if (data[data.length - 1] == 2 || data[data.length - 1] == 3) {
                received = true;
                logger.debug("Получен последний пакет запроса с адреса "+addr+" - пакет "+ i);
                clientAddress = (InetSocketAddress) addr;
            }
            else {
                logger.debug("Получен  пакет "+i+"с адреса "+addr);
            }
            result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
            i+=1;
        }
        Command command = Deserializer.deserialize(result);
        logger.debug("Команда десериализованна успешно");

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

    }
