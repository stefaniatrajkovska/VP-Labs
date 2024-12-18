package mk.finki.ukim.mk.lab1.service.implementation;

import mk.finki.ukim.mk.lab1.model.EventBooking;
import mk.finki.ukim.mk.lab1.repository.EventBookingRepository;
import mk.finki.ukim.mk.lab1.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final EventBookingRepository eventBookingRepository;

    public EventBookingServiceImpl(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public void placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking eventBooking = new EventBooking(eventName,attendeeName,attendeeAddress,(long)numberOfTickets);
        eventBookingRepository.addBookings(eventBooking);
    }

    public List<EventBooking> listBookings ()
    {
        return eventBookingRepository.listBookings();

    }
}



