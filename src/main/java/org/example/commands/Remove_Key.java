package org.example.commands;

import org.example.managers.Collection;

import org.example.utility.*;

/**
 * The delete item command
 */
public class Remove_Key implements Command {
    private Collection collection;
    private Console console;
    public Remove_Key(Console console, Collection collection){
        this.console =console;
        this.collection = collection;
    }

    public void execute(String idstr){

        collection.removeElement(ValidateId.validateId(idstr,false,collection));
        console.print("Элемент удалён");
    }
}
