package org.example.commands;

import org.example.managers.Collection;
import org.example.utility.Console;
import org.example.utility.InvalidFormatExeption;

/**
 * The command outputs information about the collection
 */
public class Info implements  Command {
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    public Info(){

    }
    @Override
    public void execute(String arg){
        String s = "Дата инициализации "+collection.getCurrentDate()+
                ", Тип коллекции - HashMap, Кол-во элементов "+collection.getCountOfElements();
        console.print(s);
    }
}
