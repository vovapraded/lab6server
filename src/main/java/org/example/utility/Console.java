package org.example.utility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.commands.ExecuteScript;
import org.example.connection.UdpServer;
import org.example.managers.Collection;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import static lombok.AccessLevel.PRIVATE;

/**
 * a class for reading and writing from the console
 */
@NoArgsConstructor(access = PRIVATE)
public  class Console  {

    public static Console getInstance() {
        if (instance == null) {
            instance = new Console();
        }
        return instance;
    }
    private static Console instance;

    private  Scanner fileScanner = null;
    private static final UdpServer udpServer = UdpServer.getInstance();
    private     Scanner defScanner = new Scanner(System.in);
    private  Scanner scanner;
    private ArrayList<String> buffer = new ArrayList<>();

    public Scanner getScanner() {
        return scanner;
    }



    public String getInput() {
        String input = null;
        checkScanner();
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }


        if (input!=null) return input;
        ArrayList<File> stack = ExecuteScript.getStack();
        ArrayList<Scanner> stackScanners = ExecuteScript.getStackScanners();
        int size = stack.size();
        if (stack.isEmpty()){
            System.exit(0);
        }

        addToSend("Чтение файла "+stack.get(stack.size()-1)+" окончено");
        stack.remove(stack.size()-1);
        ExecuteScript.setStack(stack);
        stackScanners.remove(stackScanners.size()-1);
        ExecuteScript.setStackScanners(stackScanners);
        if (stack.isEmpty()){
            selectConsoleScanner();
        }else{
            fileScanner = stackScanners.get(stackScanners.size()-1);

        }

        return  getInput();
    }
    private void  goToMenu(){
        throw new InvalidFormatExeption("Операция отменена");
    }
    public String getInputFromCommand(int minCountOfArgs,int maxCountOfArgs){
        this.addToSend("Для отмены операции введите /");
        String input = this.getInput();
        if (input.equals("/")){
            goToMenu();
        }
        int countOfArgs = input.split(" ",-1).length ;
        if (countOfArgs < minCountOfArgs || countOfArgs>maxCountOfArgs ){
            addToSend("Неверное число аргументов");
            return getInputFromCommand(minCountOfArgs,maxCountOfArgs);
        }
        return input;
    }

    public void checkScanner(){
        if (fileScanner==null){
            scanner=defScanner;
        }else {
            scanner =fileScanner;
        }
    }
    public void selectFileScanner(Scanner scanner) {
        this.fileScanner = scanner;
    }

    public void selectConsoleScanner() {
        this.fileScanner = null;
    }

    public void addToSend(String s){
        buffer.add(s);
    }
    public void send(){
        String[] array = buffer.toArray(new String[0]);
        String result = String.join("\n", array);
        udpServer.sendResponse(result.getBytes());
        buffer.clear();
    }

}