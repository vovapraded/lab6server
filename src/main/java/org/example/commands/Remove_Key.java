package org.example.commands;

import org.example.managers.Collection;

import org.example.utility.*;

/**
 * The delete item command
 */
public class Remove_Key implements Command {
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    public Remove_Key(){

    }

    public void execute(String idstr){

        collection.removeElement(ValidateId.validateId(idstr,false,collection));
        console.print("Элемент удалён");
    }
}
