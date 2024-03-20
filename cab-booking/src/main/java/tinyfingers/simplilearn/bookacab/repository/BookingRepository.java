package tinyfingers.simplilearn.bookacab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tinyfingers.simplilearn.bookacab.model.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

  @Query("SELECT b FROM Booking b WHERE b.userId = :userId")
  List<Booking> findAllByUserId(String userId);

//  @Query("DELETE b FROM Booking b WHERE b.userId = :userId")
  void deleteBookingByIdAndUserId(Long id, String userId);
}
