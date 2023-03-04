package it.business.calibrebackend.Controller;

import it.business.calibrebackend.model.User;
import it.business.calibrebackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            try {
                User toBeRegistered = userRepository.save(user);
                logger.info("Registered new user with id: " + toBeRegistered.getID());
                return ResponseEntity.ok(toBeRegistered);
            } catch (Exception e) {
                logger.error("Error while registering new user: " + e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        } else {
            logger.info("Requested to register using an already used email!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
