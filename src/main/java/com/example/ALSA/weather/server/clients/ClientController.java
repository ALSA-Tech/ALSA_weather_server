package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.locations.Location;
import com.example.ALSA.weather.server.locations.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
//TODO naming convention
    private final ClientService service;

    @Autowired // Autowire (inject) ClientService from the Spring container.
    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Client login(@RequestBody Client client) throws ClientNotFoundException{
       return service.loginClient(client);
    }

    @PostMapping("/register")
    public Client register(@RequestBody Client client) throws EmailException {
        return service.registerClient(client);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody Client client) throws ClientNotFoundException {
        service.logoutClient(client);
    }

    @GetMapping("get-locations/{clientId}")
    public List<Location> getLocations(@PathVariable int clientId) throws LocationNotFoundException, ClientNotFoundException {
        return service.getSubscriptionLocations(clientId);
    }

    @GetMapping("/search-location/{location}")
    public Location searchLocation(@PathVariable String location) throws LocationNotFoundException {
        return service.searchLocation(location);
    }

    @PostMapping("/update")
    public Client updateClient(@RequestBody Client client) throws ClientNotFoundException {
        // Typical usage: Add and remove locations from subscriptions.
        return service.updateClient(client);
    }

    @GetMapping("/connection")
    public boolean con() {
        return true;
    }

}
