package mk.finki.ukim.mk.lab1.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // avtomatsko generiranje na id
    private Long id;
    private String name;
    private String description;
    private double popularityScore;


    @ManyToOne
    private Location location;

    public Event(String name, String description, double popularityScore, Location location) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }


    public Event() {}
}
