package tinyfingers.simplilearn.bookacab.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingInfo {
  private String pickupLocation;
  private String destination;
  private LocalDateTime pickupTime;
  private int minSeats;
  private double price;
  private double distance;

}
