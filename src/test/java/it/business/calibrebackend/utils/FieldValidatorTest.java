package it.business.calibrebackend.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldValidatorTest {

    /*
     * Copied from <a href="https://gist.github.com/cjaoude/fd9910626629b53c4d25">...</a>
     *
     * Invalid but seems valid with the actual regex: "email@example.web", "email@111.222.333.44444"
     */
    @Test
    void isValidEmail() {
        List<String> validEmails = new ArrayList<>(List.of("email@example.com", "firstname.lastname@example.com", "email@subdomain.example.com", "firstname+lastname@example.com", "email@123.123.123.123", "email@[123.123.123.123]", "\"email\"@example.com", "1234567890@example.com", "email@example-one.com", "_______@example.com", "email@example.name", "email@example.museum", "email@example.co.jp", "firstname-lastname@example.com"));
        validEmails.forEach(email -> assertTrue(FieldValidator.isValidEmail(email), "Email " + email + " is not valid"));
        List<String> invalidEmails = new ArrayList<>(List.of("plainaddress", "#@%^%#$@#$@#.com", "@example.com", "Joe Smith <email@example.com>", "email.example.com", "email@example@example.com", ".email@example.com", "email.@example.com", "email..email@example.com", "あいうえお@example.com", "email@example.com (Joe Smith)", "email@example", "email@-example.com", "email@example..com", "Abc..123@example.com"));
        invalidEmails.forEach(email -> assertFalse(FieldValidator.isValidEmail(email), "Email " + email + " is a valid"));
    }

    @Test
    void isValidPassword() {
        List<String> validPasswords = new ArrayList<>(List.of("Password1@", "Password1@Password1@", "Pas2@word1@", "Pas2@word1@Pas2@word1@", "Pas2@word1@Pas2@word1@Pas2@word1@"));
        validPasswords.forEach(password -> assertTrue(FieldValidator.isValidPassword(password), "Password " + password + " is not valid"));
        List<String> invalidPasswords = new ArrayList<>(List.of("password1@", "PASSWORD1@", "Password@", "Password1"));
        invalidPasswords.forEach(password -> assertFalse(FieldValidator.isValidPassword(password), "Password " + password + " is a valid"));
    }
}