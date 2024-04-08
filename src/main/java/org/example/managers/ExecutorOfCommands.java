package org.example.managers;

import lombok.Getter;
import org.example.commands.Command;
import org.example.utility.*;

/**
 * A class for executing commands
 */
public class ExecutorOfCommands {



    private final Collection collection = Collection.getInstance();
    private  final  Console console = Console.getInstance();

    public ExecutorOfCommands(){
    }
    public void executeCommand(Command command) {
        try {
            command.execute();
        }
        catch (InvalidFormatExeption e){
            console.addToSend(e.getMessage());
            console.send();
        }
    }


//
//
//



//








}






