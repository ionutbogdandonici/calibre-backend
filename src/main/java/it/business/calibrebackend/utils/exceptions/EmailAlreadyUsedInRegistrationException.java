package it.business.calibrebackend.utils.exceptions;

public class EmailAlreadyUsedInRegistrationException extends RuntimeException {

    public EmailAlreadyUsedInRegistrationException() {
        super("Trying to use an email that's already used!");
    }
}
