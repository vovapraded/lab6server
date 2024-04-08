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
        var idstr = stringArg;
        Long id = ValidateId.validateId(idstr, true, collection);
        collection.insertElement(ticketArg);
        console.print("Билет успешно введён");
    }
}