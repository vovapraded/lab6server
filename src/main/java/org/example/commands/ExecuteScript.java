package org.example.commands;
import lombok.NoArgsConstructor;
import org.example.managers.*;
import org.example.utility.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The command to execute a script from a file
 */
public class ExecuteScript extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "Clear".hashCode();
    private static ArrayList<File> stack = new ArrayList<>();
    private static ArrayList<Scanner> stackScanners = new ArrayList<>();
    public ExecuteScript(){

    }

    public static ArrayList<File> getStack() {
        return stack;
    }

    public static ArrayList<Scanner> getStackScanners() {
        return stackScanners;
    }

    public static void setStackScanners(ArrayList<Scanner> stackScanners) {
        ExecuteScript.stackScanners = stackScanners;
    }

    public static void setStack(ArrayList<File> stack) {
        ExecuteScript.stack = stack;
    }

    @Override
    public void execute() {
            try {
                File file = new File(stringArg);
                if (!stack.contains(file)){
                    stack.add(file);

                    Scanner scanner = new Scanner(file);
                    stackScanners.add(scanner);
                    console.selectFileScanner(scanner);
                }else {
                    console.addToSend("Ошибка бесконечная рекурсия");
                }
            } catch (FileNotFoundException e) {
                throw new InvalidFormatExeption("Нет такого файла");
            }catch (SecurityException e){
                throw new InvalidFormatExeption("Нет прав доступа");
        }
        console.send();
    }
}
