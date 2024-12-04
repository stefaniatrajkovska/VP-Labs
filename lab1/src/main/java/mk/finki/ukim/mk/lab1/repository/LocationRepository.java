package mk.finki.ukim.mk.lab1.repository;

import lombok.Data;
import mk.finki.ukim.mk.lab1.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab1.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {

    public List<Location> findAll(){
        return DataHolder.locationList;
    }

    public Optional<Location> findById(Long id){
        return DataHolder.locationList.stream().filter(f -> f.getId().equals(id)).findFirst();
    }
}
