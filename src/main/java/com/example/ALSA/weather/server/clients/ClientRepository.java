package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.spring_example.X_Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * The data access service
 *
 * Interface. The Spring Data API will instantiate an object in the Spring container.
 * That instance will have methods for common database operations, so no SQL code is needed.
 * Uses the bd table specifications from DB entity (model) class Client
 *
 * CrudRepository<Entity class, primary key type>
 */

public interface ClientRepository extends CrudRepository<Client, Integer> {

    // Custom query, not part of repository's common DB operations.
    @Query(value = "SELECT a FROM Client a WHERE a.email = ?1")
    ArrayList<Client> findByEmail(String email);
}
