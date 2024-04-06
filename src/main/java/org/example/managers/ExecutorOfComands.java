package org.example.managers;

import org.example.commands.*;
import org.example.managers.*;
import org.example.utility.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * A class for executing commands
 */
public class ExecutorOfComands  {
    private  Console console;
    private Collection collection;
    private HashMap<String, Command> commands = new HashMap<String, Command>();

    public ExecutorOfComands(Console console,Collection collection){
        this.collection=collection;
        this.console = console;
        commands.put("insert",new Insert(console,collection));
        commands.put("help",new Help(console,collection));
        commands.put("remove_key",new Remove_Key(console,collection));
        commands.put("update",new Update(console,collection));
        commands.put("show",new Show(console,collection));
        commands.put("exit",new Exit(console,collection));
        commands.put("save",new Save(console,collection));
        commands.put("clear",new Clear(console,collection));
        commands.put("info",new Info(console,collection));
        commands.put("remove_greater",new Remove_Greater(console,collection));
        commands.put("remove_greater_key", new Remove_Greater_Key(console,collection));
        commands.put("print_descending", new Print_Descending(console,collection));
        commands.put("average_of_price",new Average_Of_Price(console,collection));
        commands.put("execute_script",new Execute_Script(console,collection));
        commands.put("replace_if_greater",new Replace_If_Greater(console,collection));
        commands.put("filter_less_than_venue",new Filter_Less_Than_Venue(console,collection));




    }
    public void executeComand(String cmd, String arg1) {
        if (commands.containsKey(cmd)){
            int a = arg1.isEmpty() ? 0 : 1;
            try {
            if (a!=Comands.valueOf(cmd).getCountArgs()){
                throw new InvalidFormatExeption("Неверное число аргументов");
            }
            }catch (IllegalArgumentException e){
                throw new RuntimeException("Нет такой команды");
            }

            try {
                commands.get(cmd).execute(arg1);
            } catch (IOException e) {
            }
        }
        else {
            throw new InvalidFormatExeption("Нет такой команды");
        }
    }


//
//
//



//








}






