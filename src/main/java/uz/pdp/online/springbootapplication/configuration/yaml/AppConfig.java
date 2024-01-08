package uz.pdp.online.springbootapplication.configuration.yaml;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "environments")
public class AppConfig {
    private List<String> environments;
}
