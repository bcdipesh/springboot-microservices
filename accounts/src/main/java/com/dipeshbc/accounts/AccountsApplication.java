package com.dipeshbc.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice REST API",
                description = "EazyBank Accounts microservice REST API for managing customer accounts. " +
                        "This API allows you to create new accounts, fetch account details, " +
                        "update existing accounts, and delete accounts.",
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
                description = "External documentation for the EazyBank Accounts microservice.",
                url = "https://dipeshbc.com"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
