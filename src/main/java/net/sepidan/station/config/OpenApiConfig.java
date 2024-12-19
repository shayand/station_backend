package net.sepidan.station.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = {
    @Server(url = "http://localhost:9096", description = "main env")
})
public class OpenApiConfig {

  @Value("${issuer}")
  private String issuerUrl;

  @Bean
  public OpenAPI baseOpenApi() {
    return new OpenAPI().components(new Components()).info(
            new Info().title("station api").description("guild for station app")
                .summary("station app summary")
        ).addSecurityItem(new SecurityRequirement().addList("keycloak"))
        .components(new Components().addSecuritySchemes("keycloak",
            new SecurityScheme().type(Type.OAUTH2).scheme("bearer").bearerFormat("JWT")
                .flows(new OAuthFlows().clientCredentials(
                    new OAuthFlow().authorizationUrl(
                        issuerUrl + "/protocol/openid-connect/auth"
                    ).tokenUrl(
                        issuerUrl + "/protocol/openid-connect/token"
                    )
                ))));
  }
}
