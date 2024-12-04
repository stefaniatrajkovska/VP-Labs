package mk.finki.ukim.mk.lab1.service.implementation;


import mk.finki.ukim.mk.lab1.model.Event;
import mk.finki.ukim.mk.lab1.model.Location;
import mk.finki.ukim.mk.lab1.repository.EventRepository;
import mk.finki.ukim.mk.lab1.repository.EventRepositoryI;
import mk.finki.ukim.mk.lab1.repository.LocationRepository;
import mk.finki.ukim.mk.lab1.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final EventRepositoryI eventRepositoryI;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository, EventRepositoryI eventRepositoryI) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.eventRepositoryI = eventRepositoryI;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    public List<Event> searchByName(String name){
        return eventRepository.searchByName(name);
    }

    public List<Event> searchByRating(double rating){
        return eventRepository.searchByRating(rating);
    }
    public List<Event> searchByNameAndRating(String name, double rating){
        return eventRepository.searchByNameAndRating(name,rating);
    }

    @Override
    public Optional<Event> save(String name, String description, double popularityScore, Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        return eventRepository.save(name,description,popularityScore,location);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);

    }

    @Override
    public List<Event> findAllByLocation_id(Long locationId) {
        return eventRepositoryI.findAllByLocation_Id(locationId);
    }
}
