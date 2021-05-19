package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.locations.Location;
import com.example.ALSA.weather.server.locations.LocationNotFoundException;
import com.example.ALSA.weather.server.locations.LocationService;
import com.example.ALSA.weather.server.spring_example.X_ClientNotFoundException;
import com.example.ALSA.weather.server.utils.ScorpioZHash;
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

    public Client registerClient(Client client) throws ClientNotFoundException {//Another exception ex. if user already exist
        // Store to DB. Generates ID and returns the created client object.
        return repository.save(client);
    }

    public Client loginClient(Client client) throws ClientNotFoundException {
        try {
            Client dbClient = repository.findByEmail(client.getEmail()).get(0);
            String pwdPlaintext = client.getPassword();
            String pwdHashed = dbClient.getPassword();
            ScorpioZHash scorpioZHash = new ScorpioZHash();
            if (scorpioZHash.validatePassword(pwdPlaintext, pwdHashed)) {
                return dbClient;
            }
            throw new ClientNotFoundException("Invalid password");
        } catch (IndexOutOfBoundsException e) {
            throw new ClientNotFoundException("No client with email: " + client.getEmail());
        }
    }

    public void logoutClient(Client client) throws ClientNotFoundException {
        // -||-
    }

    public List<Location> getSubscriptionLocations(Integer id) throws
            LocationNotFoundException, ClientNotFoundException {
        try {
            Client client = repository.findById(id).get();
            return locationService.getLocations(client.getLocationSubscriptions());
        } catch (NoSuchElementException e) {
            throw new ClientNotFoundException("No client with id: " + id);
        }
    }

    public Location searchLocation(String location) throws LocationNotFoundException {
        return locationService.searchLocation(location);
    }

    public Location addLocationToSubscription(String id) throws LocationNotFoundException {
        return null;
    }

    public void removeLocationFromSubscription(String id) throws LocationNotFoundException {
        // -||-
    }

}
