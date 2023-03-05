package it.business.calibrebackend.utils.exceptions;

public class InvalidEmailFormatException extends RuntimeException {

    public InvalidEmailFormatException(String message) {
        super(message);
    }
}
