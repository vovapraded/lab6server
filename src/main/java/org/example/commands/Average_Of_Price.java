package org.example.commands;

import org.example.managers.*;
import org.example.utility.*;


/**
 * The command to display the average price
 */
public class Average_Of_Price implements Command {
    private Collection collection;
    private Console console;
    public Average_Of_Price(Console console, Collection collection){
        this.console =console;
        this.collection = collection;
    }
    @Override
    public void execute(String arg) {
        if (collection.getAveragePrice() == -1) {
            console.print("Коллекция пуста");
        } else {
            console.print("Средняя цена " + collection.getAveragePrice());
        }
    }
}