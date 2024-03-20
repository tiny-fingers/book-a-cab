package tinyfingers.simplilearn.bookacab.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tinyfingers.simplilearn.bookacab.model.Booking;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BookingRepositoryTest {

  @PreAuthorize("authenticated")
  public String getMessage() {
    Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
    return "Hello " + authentication;
  }

  @Autowired
  private BookingRepository bookingRepository;

  @Test
  public void testFindAllByUserId() {
    // Given
    Booking booking1 = new Booking();
    booking1.setUserId("user");

    Booking booking2 = new Booking();
    booking2.setUserId("user");

    Booking booking3 = new Booking();
    booking3.setUserId("otherUser");

    bookingRepository.saveAllAndFlush(List.of(booking1, booking2, booking3));

    // When
    List<Booking> found = bookingRepository.findAllByUserId("user");

    // Then
    List<Booking> all = bookingRepository.findAll();
    assertThat(all).hasSize(3);
    assertThat(found).hasSize(2);
    assertThat(found.get(0).getUserId()).isEqualTo("user");
    assertThat(found.get(0).getUserId()).isEqualTo("user");
  }
}