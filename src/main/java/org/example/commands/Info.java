package org.example.commands;

import org.example.managers.Collection;
import org.example.utility.Console;
import org.example.utility.InvalidFormatExeption;

import java.io.Serial;
import java.io.Serializable;

/**
 * The command outputs information about the collection
 */
public class Info extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "Info".hashCode();

    @Override
    public void execute(){
        String s = "Дата инициализации "+collection.getCurrentDate()+
                ", Тип коллекции - HashMap, Кол-во элементов "+collection.getCountOfElements();
        console.addToSend(s);
        console.send();
    }
}
