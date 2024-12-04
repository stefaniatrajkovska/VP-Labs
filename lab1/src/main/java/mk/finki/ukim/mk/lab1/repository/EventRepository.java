package mk.finki.ukim.mk.lab1.repository;


import mk.finki.ukim.mk.lab1.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab1.model.Event;
import mk.finki.ukim.mk.lab1.model.Location;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    public List<Event> findAll() { return  DataHolder.eventList; }
    public List<Event> searchEvents(String text){
        return DataHolder.eventList.stream().filter(e -> e.getName().contains(text) || e.getDescription().contains(text)).collect(Collectors.toList());
    }

    public List<Event> searchByName(String name){
        return DataHolder.eventList.stream().filter(e -> e.getName().equals(name)).collect(Collectors.toList());
    }
    public List<Event> searchByRating(double rating){
        return DataHolder.eventList.stream().filter(e -> e.getPopularityScore() == rating).collect(Collectors.toList());
    }

    public List<Event> searchByNameAndRating(String name, double rating){
        return DataHolder.eventList.stream().filter(e->e.getName().equals(name) && e.getPopularityScore() == rating).collect(Collectors.toList());
    }

    public Optional<Event> save(String name, String description, double popularityScore, Location location){
        Event newEvent = new Event(name, description, popularityScore, location);
        DataHolder.eventList.removeIf(e -> Objects.equals(e.getName(), name));
        DataHolder.eventList.add(newEvent);
        return Optional.of(newEvent);
    }

    public Optional<Event> findById(Long id){
        return DataHolder.eventList.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id){
        DataHolder.eventList.removeIf(e -> Objects.equals(e.getId(),id));
    }




}
