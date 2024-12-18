package mk.finki.ukim.mk.lab1.service.implementation;

import mk.finki.ukim.mk.lab1.model.Location;
import mk.finki.ukim.mk.lab1.repository.LocationRepository;
import mk.finki.ukim.mk.lab1.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    @Override
    public List<Location> findAll() {
        return  locationRepository.findAll();
    }
}
