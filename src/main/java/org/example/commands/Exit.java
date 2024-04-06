package org.example.commands;

import org.example.managers.Collection;
import org.example.utility.Console;

/**
 * The shutdown command
 */
public class Exit implements Command{
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    public Exit(){

    }
    @Override
    public void execute(String arg) {
        console.print("Завершение работы");
        System.exit(0);
    }
}
