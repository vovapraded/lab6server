package org.example.commands;
import org.example.managers.*;
import org.example.utility.*;

/**
 * The clear collection command
 */
public class Clear implements Command{
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    public Clear(){

    }
    @Override
    public void execute(String arg) {
        collection.clearCollection();
        console.print("Коллекция очищена");
    }
}
