package com.example.ALSA.weather.server.dao;

import com.example.ALSA.weather.server.model.Client;

import java.util.UUID;

/*
* Functionality of database (DAO) class will be added here and
* functions for overriding methods are here!
*/
public interface ALSADatabase {

    //Register user with a given ID
    int createClient(UUID id, Client client);

    //Register a user without a given ID
    default int createClient(Client client){
        UUID id = UUID.randomUUID();
        return createClient(id, client);
    }

   //login
   int login(Client client);

   //TODO Add further functionality related to database access (getUser(id), getSubscriptions..etc)
}
