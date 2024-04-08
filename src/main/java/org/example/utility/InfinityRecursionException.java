package org.example.utility;

import java.io.Serial;

public class InfinityRecursionException extends RuntimeException {
    private String message;
    public InfinityRecursionException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
