package org.example.commands;

import org.example.dto.Ticket;
import org.example.managers.Collection;
import org.example.utility.Console;
import org.example.utility.InvalidFormatExeption;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

/**
 * The command outputs a collection
 */
public class Show extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "Show".hashCode();


    @Override
    public void execute() {
            if (collection.getHashMap().isEmpty()){
                console.addToSend("Коллекция пуста");
            }
            else{
                Arrays.sort(collection.getHashMap().values().toArray());
                for (Ticket ticket : collection.getHashMap().values()){
                    console.addToSend(ticket.toString());
                }
            }
        console.send();


    }
}
