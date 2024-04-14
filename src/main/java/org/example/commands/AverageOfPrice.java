package org.example.commands;

import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


/**
 * The command to display the average price
 */
public class AverageOfPrice extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "AverageOfPrice".hashCode();
    @Override
    public void execute() {
        var average = collection.getAveragePrice();
        if (average.isEmpty()) {
            console.addToSend("Коллекция пуста");
        } else {
            console.addToSend("Средняя цена " + average.getAsDouble());
        }
        console.send();
    }
}