package tinyfingers.simplilearn.bookacab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tinyfingers.simplilearn.bookacab.model.Booking;
import tinyfingers.simplilearn.bookacab.repository.BookingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

  private static final List<Booking> bookings = new ArrayList<>();
  private final DistanceApiService distanceApiService;
  private final PriceCalculatorApiService priceCalculatorApiService;
  private final BookingRepository bookingRepository;

  public Booking addBooking(Booking booking) {
    return bookingRepository.save(booking);
  }

  public List<Booking> getAllBookings(String userId) {
    return bookingRepository.findAllByUserId(userId);
  }

  public Booking getBookingById(int id) {
    return bookings.get(id);
  }

  public double getDistance(String from, String to) {
    return distanceApiService.calculateDistance(from, to);
  }

  public double getPrice(Double distance, String vehicleType) {
    return priceCalculatorApiService.calculatePrice(distance, vehicleType).getPrice();
  }

  @Transactional
  public void deleteBooking(Long id, String userId) {
    bookingRepository.deleteBookingByIdAndUserId(id, userId);
  }
}
