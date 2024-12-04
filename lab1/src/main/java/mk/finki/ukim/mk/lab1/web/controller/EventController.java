package mk.finki.ukim.mk.lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab1.model.Event;
import mk.finki.ukim.mk.lab1.model.Location;
import mk.finki.ukim.mk.lab1.service.implementation.EventServiceImpl;
import mk.finki.ukim.mk.lab1.service.implementation.LocationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events/events-list")
public class EventController {


    private final EventServiceImpl eventService;
    private final LocationServiceImpl locationService;

    public EventController(EventServiceImpl eventService, LocationServiceImpl locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }


        @GetMapping
        public String getEventsPage(@RequestParam(required = false) String error, @RequestParam(name="location", required = false) Long locationId, Model model, HttpServletRequest req){

        List<Event> events;
            String searchName = req.getParameter("searchName");
            String minRating = req.getParameter("minRating");

            // Пребарување по име и минимален рејтинг
            if (searchName != null && minRating != null && !minRating.isEmpty()) {
                events = eventService.searchEvents(searchName).stream()
                        .filter(e -> e.getPopularityScore() >= Double.parseDouble(minRating))
                        .collect(Collectors.toList());
            } else if (minRating != null && !minRating.isEmpty()) {
                events = eventService.listAll().stream()
                        .filter(e -> e.getPopularityScore() >= Double.parseDouble(minRating))
                        .collect(Collectors.toList());
            } else if (searchName != null) {
                events = eventService.searchEvents(searchName);
            } else {
                events = eventService.listAll();
            }

            model.addAttribute("events",events);
           // model.addAttribute("locations", locationService.findAll());
            return "listEvents";
        }






    @PostMapping("/add")
    public String saveEvent(@RequestParam String name, @RequestParam String description, @RequestParam double popularityScore, @RequestParam Long location){
        this.eventService.save(name, description, popularityScore, location);
        return "redirect:/events/events-list";
    }

    @GetMapping("/edit-form/{eventId}")
    public String editEvent(@PathVariable Long eventId, Model model){
        if (this.eventService.findById(eventId).isPresent()){
            Event event = this.eventService.findById(eventId).get();

            List<Location> locationList = locationService.findAll();
            model.addAttribute("locations",locationList);
            model.addAttribute("event",event);
            return "add-event";
        }

        return "redirect:/event?error=EventNotFound";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable(name = "id") Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events/events-list";
    }

    @GetMapping("/add-form")
    public String getAddEventPage(Model model){
        List<Location> locationList = locationService.findAll();
        model.addAttribute("locations", locationList);
        return "add-event";

    }




}
