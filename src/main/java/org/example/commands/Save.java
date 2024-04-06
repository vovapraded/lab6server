package org.example.commands;

import org.example.managers.Collection;
import org.example.managers.DumpManager;
import org.example.utility.Console;

/**
 * The command saves the collection
 */
public class Save implements Command{
    private Collection collection;
    private Console console;
    public Save(Console console,Collection collection){
        this.console =console;
        this.collection = collection;
    }

    @Override
    public void execute(String arg) {
            try {
                DumpManager.saveToFile(collection);
                console.print("Коллекция успешно сохранена");
            }
            catch (NullPointerException e){
                console.print("Создайте файл для сохранения");
            }




    }
}
