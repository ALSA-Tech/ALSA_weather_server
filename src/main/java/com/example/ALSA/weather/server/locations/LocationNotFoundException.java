package com.example.ALSA.weather.server.locations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404
public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException(String message) {
        // Pass custom error msg to client.
        super(message);
    }
}
