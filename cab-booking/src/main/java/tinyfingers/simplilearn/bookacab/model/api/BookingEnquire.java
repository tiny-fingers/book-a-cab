package tinyfingers.simplilearn.bookacab.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingEnquire {
  private String pickupLocation;
  private String destination;
  private LocalDateTime pickupTime;
  private int minSeats;
}
