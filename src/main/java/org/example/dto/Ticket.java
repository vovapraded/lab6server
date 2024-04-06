package org.example.dto;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 * a class for storing ticket data
 */
public class Ticket extends ElementsWithId implements Comparable<Ticket>{
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long price; //Поле не может быть null, Значение поля должно быть больше 0
    private Long discount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100
    private Boolean refundable; //Поле может быть null
    private TicketType type; //Поле не может быть null
    private Venue venue; //Поле не может быть null
    //конструктор с датой
    public Ticket(String name,Coordinates coordinates,Long price,Long discount,Boolean refundable,TicketType type,Venue venue){
        this.id = getFreeId(instancesTicket);
        this.name= name;
        this.coordinates=coordinates;
        Date currentDate = new Date();
        this.creationDate=currentDate;
        this.price=price;
        this.discount = discount;
        this.refundable = refundable;
        this.type = type;
        this.venue=venue;
    }

    {instancesTicket.add(this);}

        //конструктор без даты
    public Ticket(Long id,String name,Coordinates coordinates,Date creationDate,Long price,Long discount,Boolean refundable,TicketType type,Venue venue){
        this.name= name;
        this.id = id;
        this.coordinates=coordinates;
        this.creationDate =creationDate;
        this.price=price;
        this.discount = discount;
        this.refundable = refundable;
        this.type = type;
        this.venue=venue;
    }


    public Boolean getRefundable() {
        return refundable;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }


    public Long getDiscount() {
        return discount;
    }

    public Long getPrice() {
        return price;
    }

    public TicketType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Venue getVenue() {
        return venue;
    }
    public void setId(Long id){
        this.id = id;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public int compareTo(Ticket other){
        return (int) (price - other.getPrice());

    }


    @Override
    public String toString(){
        return "id "+id+
                ", name "+name+
                ", coordinates "+getCoordinates().toString()+
                ", creationDate "+ creationDate+
                ", price " +price+
                ", discount " +discount+
                ", refundable " +refundable+
                ", ticketType "+type+
                ", venue " + venue.toString();
    }
}
