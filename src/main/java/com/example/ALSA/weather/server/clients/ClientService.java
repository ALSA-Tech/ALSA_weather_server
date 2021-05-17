package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.locations.Location;
import com.example.ALSA.weather.server.locations.LocationNotFoundException;
import com.example.ALSA.weather.server.locations.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
//Todo add services

    public Client registerClient(Client client) throws ClientNotFoundException{//Another exception ex. if user already exist
        return null; //add service/functionality
    }

    public Client loginClient(Client client) throws ClientNotFoundException{
        return null; // -||-
    }

    public void logoutClient(Client client) throws ClientNotFoundException{
        // -||-
    }
    public List<Location> getSubscriptionLocations(String id) throws LocationNotFoundException{
        return null; // -||-
    }

    public Location searchLocation(String location) throws LocationNotFoundException {
        return locationService.searchLocation(location);
    }

    public Location addLocationToSubscription(String id) throws LocationNotFoundException{
        return null;
    }

    public void removeLocationFromSubscription(String id) throws LocationNotFoundException{
        // -||-
    }

}
