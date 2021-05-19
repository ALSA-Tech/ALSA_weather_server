package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.locations.Location;
import com.example.ALSA.weather.server.locations.LocationNotFoundException;
import com.example.ALSA.weather.server.locations.LocationService;
import com.example.ALSA.weather.server.utils.ScorpioZHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer (business logic layer).
 * Bridge between controller and data access (repository)
 */

@Service // Register as Singleton bean in the Spring container.
public class ClientService {

    @Autowired // Field based dependency injection
    private ClientRepository repository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ScorpioZHash scorpioZHash;

    public Client registerClient(Client client) throws ClientNotFoundException {//Another exception ex. if user already exist
        // Hash client pwd before DB storage
        client.setPassword(scorpioZHash.generateHash(client.getPassword()));
        // Store to DB. Generates ID and returns the created client object.
        Client newClient = repository.save(client);
        newClient.setPassword("hidden"); // Unnecessary to return pwd
        return newClient;
    }

    public Client loginClient(Client client) throws ClientNotFoundException {
        try {
            Client dbClient = repository.findByEmail(client.getEmail()).get(0);
            String pwdPlaintext = client.getPassword();
            String pwdHashed = dbClient.getPassword();
            // Compare pwd: plaintext <-> hashed
            if (scorpioZHash.validatePassword(pwdPlaintext, pwdHashed)) {
                dbClient.setPassword("hidden"); // Unnecessary to return pwd
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
        if (clientExists(id)) {
            Client client = repository.findById(id).get();
            return locationService.getLocations(client.getLocationSubscriptions());
        }
        throw new ClientNotFoundException("No client with id: " + id);
    }

    public Location searchLocation(String location) throws LocationNotFoundException {
        return locationService.searchLocation(location);
    }

    public Client updateClient(Client client) throws ClientNotFoundException {
        if(clientExists(client.getId())) {
            return repository.save(client);
        }
        throw new ClientNotFoundException("No client with id: " + client.getId());
    }

    private boolean clientExists(int id) {
        return repository.findById(id).isPresent();
    }

}
