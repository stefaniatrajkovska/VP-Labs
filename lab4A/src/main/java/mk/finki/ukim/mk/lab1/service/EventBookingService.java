package mk.finki.ukim.mk.lab1.service;

import mk.finki.ukim.mk.lab1.model.Event;
import mk.finki.ukim.mk.lab1.model.EventBooking;

import java.util.List;

public interface EventBookingService {
    void  placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
    List<EventBooking> listBookings();
}
