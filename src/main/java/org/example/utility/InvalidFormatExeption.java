package org.example.utility;

import java.io.Serial;

public class InvalidFormatExeption extends RuntimeException {

    public InvalidFormatExeption(String message) {
        super(message);
    }

}
