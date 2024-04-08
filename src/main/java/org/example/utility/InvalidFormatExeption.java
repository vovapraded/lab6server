package org.example.utility;

import java.io.Serial;

public class InvalidFormatExeption extends RuntimeException {
    @Serial
    private static final long serialVersionUID = "InvalidFormatExeption".hashCode();

    public InvalidFormatExeption(String message) {
        super(message);
    }

}
