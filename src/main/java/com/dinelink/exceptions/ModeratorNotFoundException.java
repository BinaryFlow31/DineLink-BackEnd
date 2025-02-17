package com.dinelink.exceptions;

public class ModeratorNotFoundException extends Exception{
    public ModeratorNotFoundException(String message) {
        super(message);
    }

    public ModeratorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModeratorNotFoundException(Throwable cause) {
        super(cause);
    }
}
