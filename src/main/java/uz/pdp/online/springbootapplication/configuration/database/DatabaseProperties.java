package uz.pdp.online.springbootapplication.configuration.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "dev")
public class DatabaseProperties {
    private Database database; // dev.database
}
