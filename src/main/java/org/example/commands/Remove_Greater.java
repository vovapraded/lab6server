package org.example.commands;


import org.example.managers.*;
import org.example.utility.*;
import org.example.dto.*;

/**
 * The remove items with a price higher than the specified one command
 */
public class Remove_Greater implements Command{
    private Collection collection;
    private Console console;

    public Remove_Greater(Console console, Collection collection){
        this.console =console;
        this.collection = collection;
    }

    @Override
    public void execute(String arg) {
            CreateTicket creator = new CreateTicket(console,collection);
            Ticket ticket = creator.createTicket(collection.getFreeId());
            collection.removeGreater(ticket);
            console.print("Удалено успешно");
    }
}