package mk.finki.ukim.mk.lab1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab1.model.Event;
import mk.finki.ukim.mk.lab1.model.EventBooking;
import mk.finki.ukim.mk.lab1.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class DataHolder {
    public static List<Event> eventList = new ArrayList<>();
    public static List<Location> locationList = new ArrayList<>();

    @PostConstruct
    public void init(){

        locationList.add(new Location("Location1","Address1", "idx1", "Description1"));
        locationList.add(new Location("Location2","Address2", "idx2", "Description2"));
        locationList.add(new Location("Location3","Address3", "idx3", "Description3"));
        locationList.add(new Location("Location4","Address4", "idx4", "Description4"));
        locationList.add(new Location("Location5","Address5", "idx5", "Description5"));




        eventList.add(new Event("Event1", "Description1", 9, locationList.get(0)));
        eventList.add(new Event("Event2", "Description2", 9,locationList.get(1)));
        eventList.add(new Event("Event3", "Description3", 8,locationList.get(2)));
        eventList.add(new Event("Event4", "Description4", 8,locationList.get(3)));
        eventList.add(new Event("Event5", "Description5", 7,locationList.get(4)));
        eventList.add(new Event("Event6", "Description6", 7,locationList.get(0)));
        eventList.add(new Event("Event7", "Description7", 6, locationList.get(1)));
        eventList.add(new Event("Event8", "Description8", 6, locationList.get(2)));
        eventList.add(new Event("Event9", "Description9", 5, locationList.get(3)));
        eventList.add(new Event("Event10","Description10",5, locationList.get(4)));
    }
}
