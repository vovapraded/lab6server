package org.example.commands;

import org.example.managers.Collection;

import org.example.utility.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * The delete item command
 */
public class RemoveKey extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "RemoveKey".hashCode();

    public void execute(){
        var idstr = stringArg;
        collection.removeElement(ValidateId.validateId(idstr,false,collection));
        console.print("Элемент удалён");
    }
}
