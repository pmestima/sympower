package net.sympower.cityzen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Not clear if this should work as an application or library
@SpringBootApplication
public class CityzenApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityzenApplication.class, args);
    }

}
