package org.example.commands;
import org.example.managers.*;
import org.example.utility.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * The command to display the collection in reverse order
 */
public class PrintDescending extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "PrintDescending".hashCode();

    @Override
    public void execute() {
        Object[] array =  collection.getHashMap().values().toArray();
        for(int i = array.length-1 ;i>=0;i--){
            console.print(array[i].toString());
        }

    }
}