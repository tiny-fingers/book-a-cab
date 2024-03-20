package tinyfingers.simplilearn.bookacab.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tinyfingers.simplilearn.bookacab.mapper.EnquiryInfoMapper;
import tinyfingers.simplilearn.bookacab.model.Booking;
import tinyfingers.simplilearn.bookacab.model.api.BookingEnquire;
import tinyfingers.simplilearn.bookacab.model.api.BookingInfo;
import tinyfingers.simplilearn.bookacab.service.BookingService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingsController.class)
class BookingsControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EnquiryInfoMapper mapper;

  @MockBean
  private BookingService bookingService;

  @Test
  @WithMockUser(username = "testUser")
  public void testDeleteBooking() throws Exception {
    Long bookingId = 1L;

    Booking booking = new Booking();
    booking.setId(bookingId);
    booking.setUserId("testUser");

    mockMvc.perform(MockMvcRequestBuilders.delete("/api/protected/cancelBooking")
                    .param("bookingId", bookingId.toString())
                    .with(jwt().jwt(jwtToken -> jwtToken.subject("testUser"))))
            .andExpect(status().isAccepted());

    verify(bookingService).deleteBooking(bookingId, "testUser");
  }

  @Test
  @WithMockUser(username = "testUser")
  public void shouldReturnBookings() throws Exception {
    Long bookingId = 1L;

    mockMvc.perform(MockMvcRequestBuilders.get("/api/protected/bookings")
                    .with(jwt().jwt(jwtToken -> jwtToken.subject("testUser"))))
            .andExpect(status().isOk());

    verify(bookingService).getAllBookings("testUser");
  }

  @Test
  public void shouldEnquireBooking() throws Exception {
    String destination = "New York";
    String pickupLocation = "Los Angeles";
    Integer minSeats = 3;
    String pickupTime = "2024-03-20T10:00:00";
    double distance = 100;
    double price = 500.0;
    BookingInfo bookingInfo = new BookingInfo();
    bookingInfo.setDistance(distance);
    bookingInfo.setPrice(price);

    when(bookingService.getDistance(pickupLocation, destination)).thenReturn(distance);
    when(bookingService.getPrice(distance, "4seat")).thenReturn(price);
    when(mapper.map(any(BookingEnquire.class))).thenReturn(bookingInfo);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/public/enquire")
                    .param("destination", destination)
                    .param("pickupLocation", pickupLocation)
                    .param("minSeats", minSeats.toString())
                    .param("pickupTime", pickupTime)
                    .with(jwt()))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.distance").value(distance))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(price));
  }
}