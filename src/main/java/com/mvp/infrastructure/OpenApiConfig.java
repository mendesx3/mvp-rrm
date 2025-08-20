package com.mvp.infrastructure;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações da documentação OpenAPI/Swagger.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API MVP RRM",
                version = "1.0",
                description = "Documentação da API para o MVP de gerenciamento de produtos e carrinho.",
                contact = @Contact(name = "Equipe MVP", email = "contato@example.com")
        )
)
public class OpenApiConfig {
}

