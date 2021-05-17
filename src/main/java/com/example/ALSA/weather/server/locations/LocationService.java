package com.example.ALSA.weather.server.locations;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic layer
 */

@Service
public class LocationService {

    // Called from ClientService
    public Location searchLocation(String location) throws LocationNotFoundException{
        return null;
    }

    // Called from ClientService
    public List<Location> getLocations(List<String> locationName) {
        return null;
    }
}
