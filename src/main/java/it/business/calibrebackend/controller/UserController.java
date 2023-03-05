package it.business.calibrebackend.controller;

import it.business.calibrebackend.model.User;
import it.business.calibrebackend.repository.UserRepository;
import it.business.calibrebackend.utils.FieldValidator;
import it.business.calibrebackend.utils.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        try {
            validateUser(user);
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new EmailAlreadyUsedInRegistrationException();
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User toBeRegistered = userRepository.save(user);
            logger.info("Registered new user with id: " + toBeRegistered.getID());
            return ResponseEntity.ok(toBeRegistered);
        } catch (InvalidEmailFormatException | EmailAlreadyUsedInRegistrationException |
                 InvalidPasswordFormatException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error while registering new user: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private void validateUser(User user) {
        if (!FieldValidator.isValidEmail(user.getEmail()))
            throw new InvalidEmailFormatException("Invalid email format");
        if (!FieldValidator.isValidPassword(user.getPassword()))
            throw new InvalidPasswordFormatException("Invalid password format");
    }
}
