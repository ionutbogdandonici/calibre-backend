package it.business.calibrebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CalibreBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalibreBackendApplication.class, args);
    }

}
