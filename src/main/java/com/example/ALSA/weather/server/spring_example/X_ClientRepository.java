package com.example.ALSA.weather.server.spring_example;

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

public interface X_ClientRepository extends CrudRepository<X_Client, Integer> {

    // Custom query, not provided by repository's common DB operations.
    @Query(value = "SELECT a FROM X_Client a WHERE a.email = ?1")
    ArrayList<X_Client> findByEmail(String email);
}
