package tinyfingers.simplilearn.bookacab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tinyfingers.simplilearn.bookacab.model.Booking;
import tinyfingers.simplilearn.bookacab.service.BookingService;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final BookingService bookingService;

  @RequestMapping("/")
  @ResponseBody
  public String home() {
    return "Welcome";
  }

  @RequestMapping("/secured")
  @ResponseBody
  public String secured(@AuthenticationPrincipal OidcUser oidcUser) {
    return "Welcome " + oidcUser.getFullName();
  }

}
