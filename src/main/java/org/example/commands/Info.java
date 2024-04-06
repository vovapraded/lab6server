package org.example.commands;

import org.example.managers.Collection;
import org.example.utility.Console;
import org.example.utility.InvalidFormatExeption;

/**
 * The command outputs information about the collection
 */
public class Info implements  Command {
    private Collection collection;
    private Console console;
    public Info(Console console,Collection collection){
        this.console =console;
        this.collection = collection;
    }
    @Override
    public void execute(String arg){
        String s = "Дата инициализации "+collection.getCurrentDate()+
                ", Тип коллекции - HashMap, Кол-во элементов "+collection.getCountOfElements();
        console.print(s);
    }
}
