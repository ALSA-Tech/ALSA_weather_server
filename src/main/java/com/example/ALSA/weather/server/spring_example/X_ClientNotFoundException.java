package com.example.ALSA.weather.server.spring_example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404
public class X_ClientNotFoundException extends RuntimeException {

    public X_ClientNotFoundException(String message) {
        // Pass custom error msg to client
        super(message);
    }
}
