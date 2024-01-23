package uz.pdp.online.springbootapplication.configuration.person.with_value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@PropertySource("classpath:application.properties")
public class Person2 {

    @Value("${person.id}")
    private Long id;

    @Value("${person.name}")
    private String name;

    @Value("${person.age}")
    private Integer age;
}
