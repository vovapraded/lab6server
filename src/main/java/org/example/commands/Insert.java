package org.example.commands;


import org.example.managers.*;
import org.example.utility.*;
import org.example.dto.*;

/**
 * Add item command
 */
public class Insert implements Command {
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();

    public Insert() {


    }

    @Override
    public void execute(String idstr) {
        Long id = ValidateId.validateId(idstr, true, collection);
        CreateTicket creator = new CreateTicket();
        collection.insertElement(creator.createTicket(id));
        console.print("Билет успешно введён");
    }
}