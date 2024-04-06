package org.example.dto;
/**
 * a class for storing coordinates data
 */
public class Coordinates {
    private Double x; //Поле не может быть null
    private long y; //Значение поля должно быть больше -618
    public Coordinates(Double x, long y) {
        this.x = x;
        this.y=y;

    }
    @Override
    public String toString(){
        return x+";"+y;
    }
}
