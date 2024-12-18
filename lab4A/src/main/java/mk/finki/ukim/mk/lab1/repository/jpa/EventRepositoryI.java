package mk.finki.ukim.mk.lab1.repository.jpa;

import mk.finki.ukim.mk.lab1.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepositoryI extends JpaRepository<Event,Long> {
    List<Event> findAllByLocation_Id(Long locationId);
}
