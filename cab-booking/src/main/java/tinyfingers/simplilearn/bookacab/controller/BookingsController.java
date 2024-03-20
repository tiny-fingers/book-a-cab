package tinyfingers.simplilearn.bookacab.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.context.DelegatingApplicationListener;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tinyfingers.simplilearn.bookacab.mapper.EnquiryInfoMapper;
import tinyfingers.simplilearn.bookacab.model.Booking;
import tinyfingers.simplilearn.bookacab.model.api.BookingEnquire;
import tinyfingers.simplilearn.bookacab.model.api.BookingInfo;
import tinyfingers.simplilearn.bookacab.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class BookingsController {

  private final BookingService bookingService;

  private final EnquiryInfoMapper mapper;
  private final DelegatingApplicationListener delegatingApplicationListener;

  @GetMapping("/protected/bookings")
  public ResponseEntity<List<Booking>> bookings(JwtAuthenticationToken authenticatedPrincipal) {
    val userId = authenticatedPrincipal.getName();
    log.trace("Logged in user: {}", userId);

    return ResponseEntity.ok(bookingService.getAllBookings(userId));
  }

  @PostMapping("/protected/confirm")
  @ResponseBody
  public Booking confirmBooking(@RequestBody BookingInfo bookingInfo, JwtAuthenticationToken authenticatedPrincipal) {
    val userId = authenticatedPrincipal.getName();
    log.info("New booking confirmed");

    return bookingService.addBooking(mapper.map(bookingInfo, userId));
  }

  @DeleteMapping("/protected/cancelBooking")
  public ResponseEntity cancelBooking(JwtAuthenticationToken authenticatedPrincipal, @RequestParam Long bookingId) {
    val userId = authenticatedPrincipal.getName();

    log.info("Canceling booking confirmed");
    bookingService.deleteBooking(bookingId, userId);
    return ResponseEntity.accepted().build();
  }

  @GetMapping("/protected")
  public ResponseEntity<String> getProtected() {
    return ResponseEntity.ok("Protected OK");
  }

  @GetMapping("/public")
  public ResponseEntity<String> getPublic() {
    return ResponseEntity.ok("Public OK");
  }

  @GetMapping("/public/enquire")
  public ResponseEntity<BookingInfo> enquireBookingInfo(
          @RequestParam String destination,
          @RequestParam String pickupLocation,
          @RequestParam Integer minSeats,
          @RequestParam String pickupTime
  ) {
    val bookingEnquire = new BookingEnquire(pickupLocation, destination, LocalDateTime.parse(pickupTime), minSeats);

    val minSeat = bookingEnquire.getMinSeats();
    var vehicleType = "other";
    if (minSeat <= 4) {
      vehicleType = "4seat";
    } else if (minSeat <= 7) {
      vehicleType = "7seat";
    }

    val distance = bookingService.getDistance(bookingEnquire.getPickupLocation(), bookingEnquire.getDestination());
    val price = bookingService.getPrice(distance, vehicleType);

    val bookingInfo = mapper.map(bookingEnquire);

    bookingInfo.setPrice(price);
    bookingInfo.setDistance(distance);

    return ResponseEntity.ok(bookingInfo);
  }
}
