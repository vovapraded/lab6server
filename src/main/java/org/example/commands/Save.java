package org.example.commands;

import org.example.managers.Collection;
import org.example.managers.DumpManager;
import org.example.utility.Console;

import java.io.Serial;
import java.io.Serializable;

/**
 * The command saves the collection
 */
public class Save extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "Save".hashCode();


    @Override
    public void execute() {
            try {
                DumpManager.saveToFile(collection);
                console.addToSend("Коллекция успешно сохранена");
            }
            catch (NullPointerException e){
                console.addToSend("Создайте файл для сохранения");
            }
        console.send();



    }
}
