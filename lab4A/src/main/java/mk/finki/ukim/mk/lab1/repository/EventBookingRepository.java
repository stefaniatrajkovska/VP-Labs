package mk.finki.ukim.mk.lab1.repository;

import mk.finki.ukim.mk.lab1.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventBookingRepository {

    List<EventBooking> bookingList = new ArrayList<>();

    public void addBookings(EventBooking eventBooking)
    {
        bookingList.add(eventBooking);
    }

    public List<EventBooking> listBookings()
    {
        return bookingList;
    }



}
