package tinyfingers.simplilearn.bookacab.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationErrorHandler implements AuthenticationEntryPoint {

  private final ObjectMapper mapper;

  @Override
  public void commence(
          final HttpServletRequest request,
          final HttpServletResponse response,
          final AuthenticationException authException
  ) throws IOException, ServletException {

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter().write("Required authentication");
    response.flushBuffer();
  }
}