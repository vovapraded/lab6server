package org.example.commands;


import org.example.managers.*;
import org.example.utility.*;
import org.example.dto.*;

/**
 * The command to delete items with an id greater than the specified number
 */
public class Remove_Greater_Key implements Command{
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();

    public Remove_Greater_Key(){

    }

    @Override
    public void execute(String idstr) {
            Long id =ValidateId.validateId(idstr,false,collection);
            int sizeBefore = collection.getCountOfElements();
            collection.removeGreaterKey(id);
            int sizeAfter = collection.getCountOfElements();
            if (sizeAfter != sizeBefore) {
                console.print("Успешно удалено");
            }else {
                console.print("Нет таких элементов");
            }


    }
}