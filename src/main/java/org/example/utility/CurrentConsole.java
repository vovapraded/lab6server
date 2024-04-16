package org.example.utility;

import lombok.Getter;
import org.common.utility.Console;
import org.example.connection.ResponsePublisher;
import org.example.connection.UdpServer;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import org.common.utility.InvalidFormatException;
public class CurrentConsole extends Console {

    private ArrayList<String> buffer = new ArrayList<>();


    @Override
    public String getInputFromCommand(int minCountOfArgs,int maxCountOfArgs){
      return null;
    }


    @Override
    public void selectFileScanner(Scanner scanner) {
    }

    @Override
    public void addToSend(String s){
        buffer.add(s);
    }
    @Override
    public void send(){
        String[] array = buffer.toArray(new String[0]);
        String result = String.join("\n", array);
        ResponsePublisher.generateResponse(result.getBytes());
        buffer.clear();
    }
}
