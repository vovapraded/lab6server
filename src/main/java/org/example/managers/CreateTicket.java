package org.example.managers;

import org.example.dto.*;
import org.example.utility.Console;
import org.example.utility.TypesOfArgs;
import org.example.utility.Validator;

/**
 * The class is responsible for creating a ticket from the console
 */

public class CreateTicket {
    private static final Console console =Console.getInstance();
    private static final Collection collection=Collection.getInstance();
    public CreateTicket(){

    }
    public static Ticket createTicket(Long id) {

        String name = "";
        while (name.isEmpty() || name.contains(" ")|| name.contains("\t")|| name.contains("\n")) {
            console.addToSend("Введите название билета");
            name = console.getInputFromCommand(1, 1);
            if (name.isEmpty()|| name.contains(" ")|| name.contains("\t")|| name.contains("\n")) {
                console.addToSend("Имя не должно быть пустым, не должно содержать пробелов");
            }
        }
        Long price = -1L;
        while (price <= 0) {
            console.addToSend("Введите цену");
            String priceStr = console.getInputFromCommand(1, 1);
            if (!Validator.validate(priceStr, TypesOfArgs.Long, false)) {
                console.addToSend("Цена должна быть числом больше 0");
            } else if (Long.parseLong(priceStr) <= 0) {
                console.addToSend("Цена должна быть числом больше 0");
            } else {
                price = Long.parseLong(priceStr);
            }

        }

        Long discount = -1L;
        while (discount <= 0 && discount != null) {
            console.addToSend("Введите скидку или пустую строку");
            String discountStr = console.getInputFromCommand(0, 1);
            if (Validator.validate(discountStr, TypesOfArgs.Long, false)) {
                discount = Long.parseLong(discountStr);
            } else if (discountStr.isEmpty()) {
                discount = null;
                break;
            }
            if (discount <= 0) {
                console.addToSend("Скидка, если есть, должна быть числом больше 0");
            }
        }


        Boolean refundable = null;
        console.addToSend("Введите возможность возврата или пустую строку");
        String refaundableStr = console.getInputFromCommand(0, 1);
        while (!Validator.validate(refaundableStr, TypesOfArgs.Boolean, true)) {
            console.addToSend("Возможность возврата, если есть, должна быть \"true\" или \"false\"");
            console.addToSend("Введите возможность возврата или пустую строку");
            refaundableStr = console.getInputFromCommand(0, 1);
        }
        if (!refaundableStr.isEmpty()) {
            refundable = Boolean.parseBoolean(refaundableStr);
        }


//        считываем ticketType

        String ticketTypeStr = "";
        console.addToSend("Введите тип билета");
        for (
                TicketType type : TicketType.values()) {
            console.addToSend(type.name());
        }
        ticketTypeStr = console.getInputFromCommand(1, 1);
        while (!Validator.validate(ticketTypeStr, TypesOfArgs.TicketType, false)) {
            console.addToSend("Вы неверно ввели тип билета");
            console.addToSend("Введите тип билета");
            for (TicketType type : TicketType.values()) {
                console.addToSend(type.name());
            }
            ticketTypeStr = console.getInputFromCommand(1, 1);
        }
        TicketType ticketType = TicketType.valueOf(ticketTypeStr.toUpperCase());
        Double x = null;
        //считываем x
        while (x == null) {
            console.addToSend("Введите координату X, где X число c плавающей точкой");
            String xstr = console.getInputFromCommand(1, 1);
            if (!Validator.validate(xstr, TypesOfArgs.Double, false)) {
                console.addToSend("X должен быть числом с плавающей точкой");
            } else {
                x = Double.parseDouble(xstr);
            }
        }
        Long y = Long.valueOf(-1000);
        String ystr;
        //считываем y
        while (y <= -618) {
            console.addToSend("Введите координату Y, где Y целое число > -618");
            ystr = console.getInputFromCommand(1, 1);
            if (!Validator.validate(ystr, TypesOfArgs.Long, false)) {
                console.addToSend("Y должен быть числом");
            } else {
                y = (long) Long.parseLong(ystr);
                if (y <= -618) {
                    console.addToSend("Y должен быть больше -618");
                }

            }
        }
        //считываем venueName
        String venueName = "";
        while (venueName.isEmpty()) {
            console.addToSend("Введите Место встречи");
            venueName = console.getInputFromCommand(1, 1);
            if (venueName.isEmpty()) {
                console.addToSend("Неверный формат ввода, вы не ввели название места встречи");
            }
        }
        //считываем venueCapacity
        Long venueCapacity = -1L;
        while (venueCapacity <= 0) {
            console.addToSend("Введите вместимость места встречи или пустую строку");
            String venueCapacityStr = console.getInputFromCommand(0, 1);
            if (venueCapacityStr.isEmpty()) {
                venueCapacity = null;
                break;
            } else if (Validator.validate(venueCapacityStr, TypesOfArgs.Long, false)) {
                venueCapacity = Long.parseLong(venueCapacityStr);
            }
            if (venueCapacity <= 0) {
                console.addToSend("Вместимость должна быть больше нуля");
            }

        }
        //считываем venueType
        VenueType venueType = null;
        while (venueType == null) {
            console.addToSend("Введите тип места встречи из предложенных или пустую строку");
            for (VenueType type : VenueType.values()) {
                console.addToSend(type.name());
            }
            String input = console.getInputFromCommand(0, 1);
            if (Validator.validate(input, TypesOfArgs.VenueType, false)) {
                venueType = VenueType.valueOf(input.toUpperCase());
            } else if (input.isEmpty()) {
                break;
            } else {
                console.addToSend("Вы неверно ввели тип места встречи");
            }
        }
        Venue venue = new Venue(venueType, venueCapacity, venueName);
        Coordinates coordinates = new Coordinates(x, y);
        Ticket ticket = new Ticket(name, coordinates, price, discount, refundable, ticketType, venue);
        ticket.setId(id);
        return ticket;
    }
}
