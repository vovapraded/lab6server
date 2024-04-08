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
        if (collection.getAveragePrice() == -1) {
            console.print("Коллекция пуста");
        } else {
            console.print("Средняя цена " + collection.getAveragePrice());
        }
    }
}