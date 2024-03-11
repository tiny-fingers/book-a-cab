package tinyfingers.simplilearn.bookacab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {
  private LocalDateTime pickUpDateTime;
  private String pickupLocation;
  private String destination;
  private int minSeats;
  private double price;
}
