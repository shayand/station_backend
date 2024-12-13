package net.sepidan.station.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = {
    @Server(url = "http://localhost:9096",description = "main env")
})
public class OpenApiConfig {
  @Bean
  public OpenAPI baseOpenApi() {
    return new OpenAPI().components(new Components()).info(
        new Info().title("station api").description("guild for station app")
            .summary("station app summary")
    );
  }
}
