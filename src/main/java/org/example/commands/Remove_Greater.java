package org.example.commands;


import org.example.managers.*;
import org.example.utility.*;
import org.example.dto.*;

/**
 * The remove items with a price higher than the specified one command
 */
public class Remove_Greater implements Command{
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    public Remove_Greater(){

    }

    @Override
    public void execute(String arg) {
            CreateTicket creator = new CreateTicket();
            Ticket ticket = creator.createTicket(collection.getFreeId());
            collection.removeGreater(ticket);
            console.print("Удалено успешно");
    }
}