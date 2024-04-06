package org.example.commands;

import org.example.dto.Ticket;
import org.example.managers.*;
import org.example.utility.*;
import org.example.utility.InvalidFormatExeption;

/**
 * The command to update the ticket
 */
public class Update implements Command {
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    public Update(){
    }
    public void execute(String idstr){
        Long id = ValidateId.validateId(idstr,false,collection);
        Ticket ticket = collection.getElement(Long.parseLong(idstr));
        collection.removeElement(id);
        try {
            Insert ins = new Insert ();
            ins.execute(idstr);
        }catch (InvalidFormatExeption e){
            collection.insertElement(ticket);
            System.out.println(e.getMessage());
        }
    }
}
