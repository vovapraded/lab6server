package org.example.dto;
/**
 * a class for storing venue data
 */
public class Venue extends ElementsWithId  {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long capacity; //Поле может быть null, Значение поля должно быть больше 0
    private VenueType type; //Поле может быть null

    public Venue(VenueType type,Long capacity,String name) {
        this.id = getFreeId(instancesVenue);
        this.type = type;
        this.capacity=capacity;
        this.name = name;
    }
    {instancesVenue.add(this);}
    public Venue(VenueType type,Long capacity,String name,Long id) {
        this.id = id;
        this.type = type;
        this.capacity=capacity;
        this.name = name;
    }


    @Override
    public String toString(){
        return  "venueName " + name+
                ", venueType "+type+
                ", venueId " + id+
                ", venueCapacity " + capacity;
    }



    public Long getCapacity() {
        return capacity;
    }


}
