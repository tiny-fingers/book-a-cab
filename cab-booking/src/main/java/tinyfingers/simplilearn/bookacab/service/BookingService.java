package tinyfingers.simplilearn.bookacab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinyfingers.simplilearn.bookacab.model.Booking;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

  private final DistanceApiService distanceApiService;

  private final PriceCalculatorApiService priceCalculatorApiService;

  private static List<Booking> bookings = new ArrayList<>();

  public List<Booking> addBooking(Booking booking) {
    bookings.add(booking);
    return bookings;
  }

  public List<Booking> getAllBookings(){
    return bookings;
  };

  public Booking getBookingById(int id){
    return bookings.get(id);
  }

  public double getDistance(String from, String to) {
    return distanceApiService.calculateDistance(from, to);
  }

  public double getPrice(Double distance, String vehicleType) {
    return priceCalculatorApiService.calculatePrice(distance, vehicleType).getPrice();
  }

}
