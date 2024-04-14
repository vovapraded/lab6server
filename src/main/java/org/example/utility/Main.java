package org.example.utility;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.example.connection.UdpServer;
import  org.example.dto.*;
import   org.example.utility.*;
import org.example.commands.*;
import org.example.managers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *Main class
 */

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            logger.debug("Сервер запускается");

            Collection collection = Collection.getInstance();
            Console console = Console.getInstance();

            DumpManager.loadFromFile(collection);
            logger.debug("Коллекция загружена. Содержит " + collection.getHashMap().size() + " элементов");
            ValidatorOfCollection validator = new ValidatorOfCollection();
            validator.validateCollection();

            UdpServer udpServer = UdpServer.getInstance();
            while (true) {
                try {
                    udpServer.run();
                } catch (RecieveDataException e) {
                    logger.error(e.getMessage());
                    console.addToSend(e.getMessage());
                    console.send();

                }
            }
        }catch (Exception e){
            logger.error("Ошибка: "+e.getClass()+" сообщение об ошибке: "+e.getMessage()+" причина ошибки: "+ e.getCause());
            e.printStackTrace();
        }

    }
}
