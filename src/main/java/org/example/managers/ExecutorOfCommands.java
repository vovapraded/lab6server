package org.example.managers;

import lombok.Getter;
import org.example.commands.Command;
import org.example.connection.UdpServer;
import org.example.utility.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class for executing commands
 */
public class ExecutorOfCommands {



    private final Collection collection = Collection.getInstance();
    private  final  Console console = Console.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(ExecutorOfCommands.class);


    public ExecutorOfCommands(){
    }
    public void executeCommand(Command command) {
        try {
            command.execute();
            logger.debug("Команда "+command.getClass().getName()+" выполнена успешно");

        }
        catch (InvalidFormatExeption e){
            console.addToSend(e.getMessage());
            logger.error(e.getMessage());
            console.send();
        }
    }


//
//
//



//








}






