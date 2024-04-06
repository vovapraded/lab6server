package org.example.commands;

import org.example.managers.Collection;
import org.example.utility.Console;

/**
 * The shutdown command
 */
public class Exit implements Command{
    private Collection collection;
    private Console console;
    public Exit(Console console,Collection collection){
        this.console =console;
        this.collection = collection;
    }
    @Override
    public void execute(String arg) {
        console.print("Завершение работы");
        System.exit(0);
    }
}
