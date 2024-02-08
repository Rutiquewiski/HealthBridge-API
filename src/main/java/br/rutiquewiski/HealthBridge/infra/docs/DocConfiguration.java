package br.rutiquewiski.HealthBridge.infra.docs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("HealthBridge API")
                        .description(" REST API for the HealthBridge application, containing functionalities for CRUD operations of doctors, dentists and patients, as well as appointment scheduling and cancellation")
                        .contact(new Contact()
                                .name("Gustavo Rutiquewiski")
                                .email("gustavo.rutiquewiski@outlook.com")));
    }
}
