package org.example.connection;

import lombok.Getter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import com.google.common.primitives.Bytes;
import org.example.commands.Command;
import org.example.managers.ExecutorOfCommands;
import org.example.utility.Deserializer;
import org.example.utility.InvalidFormatExeption;
import org.example.utility.PropertyUtil;


import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

import static org.apache.commons.lang3.ArrayUtils.toPrimitive;

public class UdpServer {

    private final InetSocketAddress serverAddress;
    private final ExecutorOfCommands executor= new ExecutorOfCommands();
    private final ByteBuffer buffer = ByteBuffer.allocate(1024);



    private  InetSocketAddress clientAddress;
    private final int PACKET_SIZE = 1024;
    private final int DATA_SIZE = PACKET_SIZE - 1;

    private DatagramSocket datagramSocket;
    private boolean running=true;
    @Getter
    private static UdpServer Instance = new UdpServer();

    private UdpServer()  {
        this.serverAddress = new InetSocketAddress(PropertyUtil.getAddress(),PropertyUtil.getPort());
        openNewSocket();



    }


    private void openNewSocket(){
        try {
            this.datagramSocket = new DatagramSocket(serverAddress);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() {
         Command commandFromClient = null;
        while (running) {
            Pair<Command, SocketAddress> dataPair;
            System.out.println("a");
            try {
                dataPair = receiveData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            commandFromClient = dataPair.getKey();
            var clientAddr = dataPair.getValue();
            clientAddress=  (InetSocketAddress)clientAddr;

            if (commandFromClient!=null){
                try {
                    executor.executeCommand(commandFromClient);

                }
                catch (InvalidFormatExeption e){

                }

            }



        }
    }
    public void connectToClient(SocketAddress addr) throws SocketException {
        datagramSocket.connect(addr);
    }

    public void disconnectFromClient() {
        datagramSocket.disconnect();
    }



    public Pair<Command, SocketAddress> receiveData() {
        var received = false;
        var result = new byte[0];
        SocketAddress addr = null;
        while (!received){
            var data = new byte[PACKET_SIZE];
            var dp = new DatagramPacket(data, PACKET_SIZE);
            try {
                System.out.println("b");
                datagramSocket.receive(dp);
                System.out.println(dp);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (data[data.length - 1] == 1) {
                received = true;
                addr = dp.getSocketAddress();
            }

            result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
        }
        System.out.println();



        return new ImmutablePair<>(Deserializer.deserialize(result), addr);
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
                System.out.println("No client address available.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
