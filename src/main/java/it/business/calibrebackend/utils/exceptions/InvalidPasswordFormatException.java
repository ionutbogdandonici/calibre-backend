package it.business.calibrebackend.utils.exceptions;

public class InvalidPasswordFormatException extends RuntimeException {

    public InvalidPasswordFormatException(String message) {
        super(message);
    }
}
