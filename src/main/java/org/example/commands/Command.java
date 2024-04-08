package org.example.commands;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.Ticket;
import org.example.managers.*;
import org.example.utility.*;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

/**
 * Interface for the command
 */
@Setter
@Getter
@NoArgsConstructor
public abstract class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "Command".hashCode();
    public abstract void execute();
    public void prepareToSend(String stringArg ,boolean ticketArgIsNeeded){
        if (!stringArg.isEmpty()) this.setStringArg(stringArg);
        if (ticketArgIsNeeded ) {
            Validator.validate(stringArg,TypesOfArgs.Long,false);
            var ticket= CreateTicket.createTicket(null);
            this.setTicketArg(ticket);
        }
    }
    protected String stringArg=null;
    protected Ticket ticketArg=null;
    protected static final Collection collection = Collection.getInstance();
    protected static final  Console console = Console.getInstance();

}
