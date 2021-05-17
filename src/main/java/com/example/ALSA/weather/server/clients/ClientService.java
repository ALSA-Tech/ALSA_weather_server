package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.locations.Location;
import com.example.ALSA.weather.server.locations.LocationNotFoundException;
import com.example.ALSA.weather.server.locations.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Service layer (business logic layer).
 * Bridge between controller and data access (repository)
 */

@Service // Register as Singleton bean in the Spring container.
public class ClientService {

    @Autowired // Field based dependency injection
    private ClientRepository repository;

    @Autowired // Field based dependency injection
    private LocationService locationService;

    public Location searchLocation(String location) throws LocationNotFoundException {
        return locationService.searchLocation(location);
    }

}
