package com.example.ALSA.weather.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.UUID;

public class Client {
    private final UUID id;
    private final String name;
    private final String password;
    private ArrayList<String> locationSubscriptions;

    public Client(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("password") String password,
                  @JsonProperty("locationSubscriptions") ArrayList<String> locationSubscriptions ) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.locationSubscriptions =  locationSubscriptions;;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getLocationSubscriptions() {
        return locationSubscriptions;
    }
}
