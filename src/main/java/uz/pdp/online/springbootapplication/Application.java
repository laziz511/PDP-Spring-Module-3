package uz.pdp.online.springbootapplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Doc - Swagger and OpenAPI",
                version = "2.3",
                contact = @Contact(
                        name = "Laziz Djuraev",
                        email = "laziz.dju@gmail.com",
                        url = "https://github.com/laziz511"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://springdoc.org"
                ),
                termsOfService = "https://swagger.io/terms/",
                description = "This document is for learning Spring Doc and Swagger"
        ),
        externalDocs = @ExternalDocumentation(
                description = "SpringDOC version 2",
                url = "https://springdoc.org/v/2"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Development-Server"
                )
        }
)

@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public GroupedOpenApi userOpenApi() {
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/items/**")
                .build();
    }
    @Bean
    public GroupedOpenApi adminOpenApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/stores/**")
                .build();
    }

}
