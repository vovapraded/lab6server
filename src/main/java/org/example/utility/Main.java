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
/**
 *Main class
 */

public class Main {
    public static void main(String[] args) {
        Collection collection =Collection.getInstance();
        Console console = Console.getInstance();
        DumpManager.loadFromFile(collection);
        ValidatorOfCollection validator = new ValidatorOfCollection();
        validator.validateCollection();
        UdpServer udpServer=UdpServer.getInstance();
        udpServer.run();



    }
}
