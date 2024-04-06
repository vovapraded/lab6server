package org.example.commands;

import org.example.dto.Ticket;
import org.example.managers.*;
import org.example.utility.*;
import org.example.utility.InvalidFormatExeption;

/**
 * The command to update the ticket
 */
public class Update implements Command {
    private Collection collection;
    private Console console;
    public Update(Console console, Collection collection){
        this.console =console;
        this.collection = collection;
    }
    public void execute(String idstr){
        Long id = ValidateId.validateId(idstr,false,collection);
        Ticket ticket = collection.getElement(Long.parseLong(idstr));
        collection.removeElement(id);
        try {
            Insert ins = new Insert (console,collection);
            ins.execute(idstr);
        }catch (InvalidFormatExeption e){
            collection.insertElement(ticket);
            System.out.println(e.getMessage());
        }
    }
}
