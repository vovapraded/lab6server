package org.example.managers;

import org.common.commands.Command;
import org.common.managers.Collection;
import org.common.utility.Console;
import org.common.utility.InvalidFormatException;
import org.example.utility.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class for executing commands
 */
public class ExecutorOfCommands {



    private final Collection collection = Collection.getInstance();
    private  final Console console;
    private static final Logger logger = LoggerFactory.getLogger(ExecutorOfCommands.class);


    public ExecutorOfCommands( Console console){
        this.console = console;
    }
    public void executeCommand(Command command) throws InvalidFormatException {
            command.setConsole(console);
            command.execute();
            logger.debug("Команда "+command.getClass().getName()+" выполнена успешно");
    }


//
//
//



//








}






