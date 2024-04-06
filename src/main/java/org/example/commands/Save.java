package org.example.commands;

import org.example.managers.Collection;
import org.example.managers.DumpManager;
import org.example.utility.Console;

/**
 * The command saves the collection
 */
public class Save implements Command{
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    public Save(){

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
