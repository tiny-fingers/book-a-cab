package tinyfingers.simplilearn.bookacab.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import tinyfingers.simplilearn.bookacab.model.Booking;
import tinyfingers.simplilearn.bookacab.service.BookingService;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
@Slf4j
public class BookingsController {

  private final BookingService bookingService;

  @GetMapping
  public String bookings(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
    model.addAttribute("bookings", bookingService.getAllBookings());
    return "bookings";
  }

  @GetMapping("/new")
  public String book(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
    model.addAttribute("booking", new Booking(LocalDateTime.now(), "", "", 2, 0));
    model.addAttribute("username", oidcUser.getName());
    return "new-booking";
  }

  @PostMapping("/confirmed")
  public String newBooking(@ModelAttribute Booking booking, Model model, @AuthenticationPrincipal OidcUser oidcUser) {
    log.info("newBooking: {}", booking);
    log.info("price: {}", booking.getPrice());

    bookingService.addBooking(booking);

    return "booking-success";
  }

  @PostMapping("/enquire")
  public String enquireBookingInfo(@ModelAttribute Booking booking, Model model, @AuthenticationPrincipal OidcUser oidcUser) {
    val minSeat = booking.getMinSeats();
    var vehicleType = "other";
    if (minSeat <=4) {
      vehicleType = "4seat";
    } else if (minSeat <= 7) {
      vehicleType = "7seat";
    }

    val distance = bookingService.getDistance(booking.getPickupLocation(), booking.getDestination());
    val price = bookingService.getPrice(distance, vehicleType);

    model.addAttribute("booking", booking);
    model.addAttribute("distance", distance);
    model.addAttribute("price", price);

    bookingService.addBooking(booking);

    return "confirm-booking";
  }
}
