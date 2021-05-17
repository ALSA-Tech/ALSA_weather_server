package com.example.ALSA.weather.server.spring_example;

import com.example.ALSA.weather.server.locations.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Service layer (business logic layer).
 * Bridge between controller and data access (repository)
 */

@Service // Register as Singleton bean in the Spring container.
public class X_ClientService {

    @Autowired // Field based dependency injection
    private X_ClientRepository repository;

    public Iterable<X_Client> getClients() {
        return repository.findAll();
    }

    public X_Client getClient(Integer id) throws X_ClientNotFoundException {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new X_ClientNotFoundException("No client with id: " + id);
        }
    }

    public X_Client addClient(X_Client client) {
        return repository.save(client); // Returns new client from DB, incl AI id.
    }

    public X_Client updateClient(X_Client client) {
        return repository.save(client); // Returns updated client from DB
    }

    public void deleteClient(int id) {
        repository.deleteById(id);
    }

    /*
     * NOTE:
     * repository.save() is used for both add (create) and update.
     * The DB layer works similar to hashmap. If the primary key (id) matches a record
     * in the DB, the record is updated. Else, a new record is created. The primary key
     * (id) that is passed is then ignored, since auto increment is used. The new record
     * is returned as an object with the id that was assigned by the DB.
     *
     * Because of the above: When creating new client object, no id attribute is needed
     * in the POST body.
     */




}
