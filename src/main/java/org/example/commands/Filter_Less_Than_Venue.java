package org.example.commands;


import org.example.managers.*;
import org.example.utility.*;
import org.example.dto.*;

import java.util.ArrayList;

/**
 * Output elements with venue.capacity less than this number
 */
public class Filter_Less_Than_Venue implements Command{
    private Collection collection;
    private Console console;

    public Filter_Less_Than_Venue(Console console, Collection collection){
        this.console =console;
        this.collection = collection;
    }

    @Override
    public void execute(String capacityStr) {
        //если у каких-то билетов capacity=null то они в любом случае выписываются
            if (!Validator.validate(capacityStr,TypesOfArgs.Long,true)){
                throw new InvalidFormatExeption("Вместимость должна быть числом");
            }
            Long capacity= Long.parseLong(capacityStr);
            ArrayList<Ticket> filtered = collection.filterLessThanVenue(capacity);
            if (filtered.isEmpty()){
                console.print("Нет таких элементов");
            }
            for (Ticket ticket : filtered){
                console.print(ticket.toString());
            }


    }
}