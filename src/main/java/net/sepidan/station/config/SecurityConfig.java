package net.sepidan.station.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Profile("!test")
public class SecurityConfig {

  @Value("${iam.issuer}")
  private String issuer;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) ->
            authorize.requestMatchers("/", "/api-docs/**").permitAll()
                .anyRequest().authenticated()
        )
        .oauth2ResourceServer((oauth2) -> oauth2
            .jwt(Customizer.withDefaults())
        );
    return http.build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return JwtDecoders.fromIssuerLocation(issuer);
  }
}
