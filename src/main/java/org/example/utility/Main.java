package org.example.utility;

import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import  org.example.dto.*;
import   org.example.utility.*;
import org.example.commands.*;
import org.example.managers.*;
/**
 *Main class
 */

public class Main {
    public static void main(String[] args) {
        Collection collection =  Collection.getInstance();
        Console console = Console.getInstance();
        ExecutorOfComands executor =new ExecutorOfComands();
        ParseInput parseInput = new ParseInput();
        DumpManager.loadFromFile(collection);
        ValidatorOfCollection validator = new ValidatorOfCollection();
        validator.validateCollection();
        while (true){
            String s = console.getInput();
            if (!s.isEmpty()) {
                try {
                    parseInput.parseInput(s);
                    String cmd = parseInput.getArg1();
                    String arg2= parseInput.getArg2();
                    if (parseInput.getArg3Exist()==1){
                        throw new InvalidFormatExeption("Слишком много аргументов");
                    }
                    executor.executeComand(cmd,arg2);
                } catch (InvalidFormatExeption e) {
                    console.print(e.getMessage());
                }
            }
        }
    }
}
