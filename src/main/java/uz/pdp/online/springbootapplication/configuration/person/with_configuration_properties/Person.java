package uz.pdp.online.springbootapplication.configuration.person.with_configuration_properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private Long id;
    private String name;
    private Integer age;
}
