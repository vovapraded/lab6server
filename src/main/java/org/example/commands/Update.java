package org.example.commands;

import org.example.dto.Ticket;
import org.example.managers.*;
import org.example.utility.*;
import org.example.utility.InvalidFormatExeption;

import java.io.Serial;
import java.io.Serializable;

/**
 * The command to update the ticket
 */
public class Update extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "Update".hashCode();


    public void execute(){
        var idstr = stringArg;
        Long id = ValidateId.validateId(idstr,false,collection);
        Ticket ticket = collection.getElement(Long.parseLong(idstr));
        collection.removeElement(id);
            Insert ins = new Insert ();
            ins.setStringArg(stringArg);
            ins.setTicketArg(ticketArg);
            ins.execute();

    }
}
