package org.example.commands;
import org.example.managers.*;
import org.example.utility.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The command to execute a script from a file
 */
public class Execute_Script implements Command{
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    private static ArrayList<File> stack = new ArrayList<>();
    private static ArrayList<Scanner> stackScanners = new ArrayList<>();
    public Execute_Script(){

    }

    public static ArrayList<File> getStack() {
        return stack;
    }

    public static ArrayList<Scanner> getStackScanners() {
        return stackScanners;
    }

    public static void setStackScanners(ArrayList<Scanner> stackScanners) {
        Execute_Script.stackScanners = stackScanners;
    }

    public static void setStack(ArrayList<File> stack) {
        Execute_Script.stack = stack;
    }

    @Override
    public void execute(String arg) {
            try {
                File file = new File(arg);
                if (!stack.contains(file)){
                    stack.add(file);

                    Scanner scanner = new Scanner(file);
                    stackScanners.add(scanner);
                    console.selectFileScanner(scanner);
                }else {
                    console.print("Ошибка бесконечная рекурсия");
                }
            } catch (FileNotFoundException e) {
                throw new InvalidFormatExeption("Нет такого файла");
            }catch (SecurityException e){
                throw new InvalidFormatExeption("Нет прав доступа");
        }
    }
}
