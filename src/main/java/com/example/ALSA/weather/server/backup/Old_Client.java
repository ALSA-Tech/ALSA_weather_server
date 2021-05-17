package com.example.ALSA.weather.server.backup;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.UUID;

public class Old_Client {
    private UUID id;
    private String name;
    private String password;
    private ArrayList<String> locationSubscriptions;

    public Old_Client(@JsonProperty("id") UUID id,
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

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", locationSubscriptions=" + locationSubscriptions +
                '}';
    }
}
