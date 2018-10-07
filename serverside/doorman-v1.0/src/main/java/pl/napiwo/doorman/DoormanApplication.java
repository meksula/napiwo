package pl.napiwo.doorman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 * @author
 * Karol Meksuła
 * 01-10-2018
 * */

@SpringBootApplication
@EnableJdbcHttpSession
public class DoormanApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoormanApplication.class, args);
    }
}
