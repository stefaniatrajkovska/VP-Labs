package mk.finki.ukim.mk.lab1.service;

import mk.finki.ukim.mk.lab1.model.EventBooking;

public interface EventBookingService {
    EventBooking  placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
}
