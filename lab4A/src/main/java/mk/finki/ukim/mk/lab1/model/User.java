package mk.finki.ukim.mk.lab1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "event-user")
public class User {

    @Id
    String username;
    String password;
    String name;
    String surname;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Event> events;

    public User() {

    }
}
