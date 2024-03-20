package tinyfingers.simplilearn.bookacab.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import tinyfingers.simplilearn.bookacab.model.Booking;
import tinyfingers.simplilearn.bookacab.model.api.BookingEnquire;
import tinyfingers.simplilearn.bookacab.model.api.BookingInfo;

@Mapper(componentModel = "spring")
@Component
public interface EnquiryInfoMapper {
  BookingInfo map(BookingEnquire bookingEnquire);

  Booking map(BookingInfo bookingEnquire);

  default Booking map(BookingInfo bookingEnquire, String userId) {
    Booking booking = map(bookingEnquire);
    booking.setUserId(userId);
    return booking;
  }
}