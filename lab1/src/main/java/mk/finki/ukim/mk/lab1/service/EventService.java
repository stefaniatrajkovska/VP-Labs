package mk.finki.ukim.mk.lab1.service;

import mk.finki.ukim.mk.lab1.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    List<Event> searchByName(String name);
    List<Event> searchByRating(double rating);
    List<Event> searchByNameAndRating(String name, double rating);
    Optional<Event> save(String name, String description, double popularityScore, Long locationId);
    Optional<Event> findById(Long id);
    void deleteById(Long id);

    List<Event> findAllByLocation_id(Long locationId);
}
