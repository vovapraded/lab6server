package org.example.commands;


import org.example.managers.*;
import org.example.utility.*;
import org.example.dto.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Add item command
 */
public class Insert extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "Insert".hashCode();

    @Override
    public void execute() {
        var idStr = stringArg;
        Long id = ValidateId.validateId(idStr, true, collection);
        ticketArg.setId(id);
        collection.insertElement(ticketArg);
        console.addToSend("Билет успешно введён");
        console.send();
    }
}