package com.example.ALSA.weather.server.clients;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404
public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message) {
        // Pass custom error msg to client.
        super(message);
    }
}
