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
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    private HashMap<String, Command> commands = new HashMap<String, Command>();

    public ExecutorOfComands(){

        commands.put("insert",new Insert());
        commands.put("help",new Help());
        commands.put("remove_key",new Remove_Key());
        commands.put("update",new Update());
        commands.put("show",new Show());
        commands.put("exit",new Exit());
        commands.put("save",new Save());
        commands.put("clear",new Clear());
        commands.put("info",new Info());
        commands.put("remove_greater",new Remove_Greater());
        commands.put("remove_greater_key", new Remove_Greater_Key());
        commands.put("print_descending", new Print_Descending());
        commands.put("average_of_price",new Average_Of_Price());
        commands.put("execute_script",new Execute_Script());
        commands.put("replace_if_greater",new Replace_If_Greater());
        commands.put("filter_less_than_venue",new Filter_Less_Than_Venue());




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






