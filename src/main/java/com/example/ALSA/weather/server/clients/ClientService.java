package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.locations.Location;
import com.example.ALSA.weather.server.locations.LocationNotFoundException;
import com.example.ALSA.weather.server.locations.LocationService;
import com.example.ALSA.weather.server.utils.ScorpioZHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

    public Client registerClient(Client client) throws EmailException {
        if(emailExists(client.getEmail())) {
            throw new EmailException("Email taken: " + client.getEmail());
        } else {
            // Hash client pwd before DB storage
            client.setPassword(scorpioZHash.generateHash(client.getPassword()));
            client.setLocationSubscriptions(removeDuplicates(client.getLocationSubscriptions()));
            // Store to DB. Generates ID and returns the created client object.
            Client newClient = repository.save(client);
            newClient.setPassword("hidden"); // Unnecessary to return pwd
            return newClient;
        }
    }

    public Client  loginClient(Client client) throws ClientNotFoundException, EmailException {
        Client dbClient = getByEmail(client.getEmail());
        String pwdPlaintext = client.getPassword();
        String pwdHashed = dbClient.getPassword();
        // Compare pwd: plaintext <-> hashed
        if (scorpioZHash.validatePassword(pwdPlaintext, pwdHashed)) {
            dbClient.setPassword("hidden"); // Unnecessary to return pwd
            return dbClient;
        }
        throw new ClientNotFoundException("Invalid password");
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
            Client dbClient = repository.findById(client.getId()).get();
            // Only allow updates on fields: name & locationSubscription
            dbClient.setName(client.getName());
            dbClient.setLocationSubscriptions(client.getLocationSubscriptions());
            // Remove potential duplicates
            dbClient.setLocationSubscriptions(removeDuplicates(dbClient.getLocationSubscriptions()));
            // Store changes to DB
            repository.save(dbClient);
            // Hide pwd and return updated client
            dbClient.setPassword("hidden");
            return dbClient;
        }
        throw new ClientNotFoundException("No client with id: " + client.getId());
    }

    private boolean clientExists(int id) {
        return repository.findById(id).isPresent();
    }

    private Client getByEmail(String email) throws ClientNotFoundException, EmailException{
        ArrayList<Client> clients = repository.findByEmail(email);
        if(clients.size() == 1) { // Should be the case
            return clients.get(0);
        } else if (clients.isEmpty()) {
            throw new ClientNotFoundException("No client with email: " + email);
        } else {
            // This should not be possible, but just in case.
            throw new EmailException("Invalid number of email matches.");
        }
    }

    private boolean emailExists(String email) {
        try {
            getByEmail(email);
            return true;
        } catch (ClientNotFoundException | EmailException e) {
            return false;
        }
    }

    private ArrayList<String> removeDuplicates(List<String> locationList) {
        HashSet<String> checker = new HashSet<>();
        ArrayList<String> processedList = new ArrayList<>();
        if(locationList != null) {
            for (String location : locationList) {
                if (checker.add(location)) {
                    processedList.add(location);
                }
            }
        }
        return processedList;
    }

}
