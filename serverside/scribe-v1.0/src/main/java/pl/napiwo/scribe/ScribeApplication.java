package pl.napiwo.scribe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author
 * Karol Meksuła
 * 05-10-2018
 * */

@SpringBootApplication
@Slf4j
public class ScribeApplication {
    public static String host;

    public static void main(String[] args) {
        SpringApplication.run(ScribeApplication.class, args);

        try {
            ScribeApplication.host = args[0];
        } catch (ArrayIndexOutOfBoundsException exception) {
            ScribeApplication.host = "http://localhost:";
        }

        log.info("Run with host: " + ScribeApplication.host);
    }
}
