package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.locations.Location;
import com.example.ALSA.weather.server.locations.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/example/clients")
public class ClientController {

    private final ClientService service;

    @Autowired // Autowire (inject) ClientService from the Spring container.
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("search-location/{location}")
    public Location searchLocation(@PathVariable String location) throws LocationNotFoundException {
        return service.searchLocation(location);
    }



}
