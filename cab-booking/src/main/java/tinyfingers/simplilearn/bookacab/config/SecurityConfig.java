package tinyfingers.simplilearn.bookacab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class SecurityConfig {
  private final AuthenticationErrorHandler authenticationErrorHandler;
  @Value("${okta.oauth2.issuer}")
  private String issuer;
  @Value("${okta.oauth2.client-id}")
  private String clientId;

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests
                    (auth -> auth
                            .requestMatchers("/api/protected", "/api/protected/**").authenticated()
                            .anyRequest().permitAll())
            .cors(Customizer.withDefaults())
            .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(Customizer.withDefaults())
                    .authenticationEntryPoint(authenticationErrorHandler));

    return http.build();
  }

  private LogoutHandler logoutHandler() {
    return (request, response, authentication) -> {
      try {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + baseUrl);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    };
  }
}
