package org.example.commands;


import org.example.managers.*;
import org.example.utility.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * The command to delete items with an id greater than the specified number
 */
public class RemoveGreaterKey extends Command implements  Serializable {
    @Serial
    private static final long serialVersionUID = "RemoveGreaterKey".hashCode();


    @Override
    public void execute() {
            var idstr=stringArg;
            Long id =ValidateId.validateId(idstr,false,collection);
            int sizeBefore = collection.getCountOfElements();
            collection.removeGreaterKey(id);
            int sizeAfter = collection.getCountOfElements();
            if (sizeAfter != sizeBefore) {
                console.addToSend("Успешно удалено");
            }else {
                console.addToSend("Нет таких элементов");
            }
        console.send();



    }
}