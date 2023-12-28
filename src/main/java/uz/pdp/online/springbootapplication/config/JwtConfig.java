package uz.pdp.online.springbootapplication.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {
    private String secret;
    private long expiration;
    private String header;
    private String prefix;
    private String uri;
}
