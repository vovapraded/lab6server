package org.example.managers;

import org.example.dto.ElementsWithId;
import org.example.dto.Ticket;
import org.example.dto.Venue;
import org.example.dto.VenueType;
import org.example.utility.Console;
import org.example.utility.InvalidFormatExeption;
import org.example.utility.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.collections4.CollectionUtils;


import java.util.*;

/**
 * A class that checks the collection for the correctness of the data
 */
public class ValidatorOfCollection {
    private static final Logger logger = LoggerFactory.getLogger(ValidatorOfCollection.class);

    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    public boolean validateCollection() {
        Set<Long> setTicketId = new HashSet<>();
        Set<Long> setVenueId = new HashSet<>();

        for (Ticket ticket: Ticket.getInstancesTicket()){
            setTicketId.add(ticket.getId());
        }
        for (Venue venue: Venue.getInstancesVenue()){
            setVenueId.add(venue.getId());
        }
        if (setVenueId.size()!=Venue.getInstancesVenue().size() || setTicketId.size()!=Ticket.getInstancesTicket().size()){

            logger.error("Ошибка не уникальности id  в файле");
            collection.clearCollection();
            logger.debug("Hashmap очищен, в файле не валидные данные");
        }
        for (Long id: collection.getHashMap().keySet()){
            if (!Objects.equals(id, collection.getHashMap().get(id).getId())){
                logger.error("Ошибка. Не совпадает id "+id+" билета с ключом");
                collection.clearCollection();
                logger.debug("Hashmap очищен, в файле не валидные данные");


                return false;

            }
        }
        logger.debug("Коллекция валидна");
        return true;


    }
}