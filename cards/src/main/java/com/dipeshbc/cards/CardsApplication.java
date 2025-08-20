package com.dipeshbc.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(
                title = "Cards Microservice REST API",
                description = "EazyBank Cards microservice REST API for managing customer cards. " +
                        "This API allows you to create new cards, fetch card details, " +
                        "update card details, and delete cards.",
                version = "v1",
                contact = @Contact(
                        name = "Dipesh B C",
                        email = "bcdipeshwork@gmail.com",
                        url = "https://dipeshbc.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "External documentation for the EazyBank Cards microservice REST API.",
                url = "https://dipeshbc.com"
        )
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }

}
