package com.proyecto.reservavuelos.configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "BearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT"
)
@OpenAPIDefinition(
        info = @Info(
                title = "API Documentation"
        ),
        servers = {
                @Server(url = "http://localhost:8080/swagger-ui.html",description = "Production server"),
                @Server(url = "http://localhost:8080",description = "Development server")
        }
)
public class SwaggerConfig {
}
