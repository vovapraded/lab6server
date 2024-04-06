package org.example.commands;
import org.example.managers.*;
import org.example.utility.*;

/**
 * The clear collection command
 */
public class Clear implements Command{
    private Collection collection;
    private Console console;
    public Clear(Console console,Collection collection){
        this.console =console;
        this.collection = collection;
    }
    @Override
    public void execute(String arg) {
        collection.clearCollection();
        console.print("Коллекция очищена");
    }
}
