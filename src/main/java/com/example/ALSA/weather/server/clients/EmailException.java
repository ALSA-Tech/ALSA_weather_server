package com.example.ALSA.weather.server.clients;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
public class EmailException extends RuntimeException {

    public EmailException(String message) {
        // Pass custom error msg to client.
        super(message);
    }
}