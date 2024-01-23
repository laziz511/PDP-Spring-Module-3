package uz.pdp.online.springbootapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import uz.pdp.online.springbootapplication.configuration.database.DatabaseProperties;
import uz.pdp.online.springbootapplication.configuration.person.with_configuration_properties.Person;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({DatabaseProperties.class, Person.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
