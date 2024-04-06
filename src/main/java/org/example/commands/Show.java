package org.example.commands;

import org.example.dto.Ticket;
import org.example.managers.Collection;
import org.example.utility.Console;
import org.example.utility.InvalidFormatExeption;

import java.util.Arrays;
import java.util.Collections;

/**
 * The command outputs a collection
 */
public class Show implements Command{
    private Collection collection;
    private Console console;
    public Show(Console console, Collection collection){
        this.console =console;
        this.collection = collection;
    }

    @Override
    public void execute(String arg) {
            if (collection.getHashMap().isEmpty()){
                console.print("Коллекция пуста");
            }
            else{
                Arrays.sort(collection.getHashMap().values().toArray());
                for (Ticket ticket : collection.getHashMap().values()){
                    console.print(ticket.toString());
                }
            }

    }
}
