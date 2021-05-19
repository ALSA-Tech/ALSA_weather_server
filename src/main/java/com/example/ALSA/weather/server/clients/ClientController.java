package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.locations.Location;
import com.example.ALSA.weather.server.locations.LocationNotFoundException;
import com.example.ALSA.weather.server.spring_example.X_Client;
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
    public Client login(@RequestBody Client client) {
       return service.loginClient(client);
    }

    @PostMapping("/register")
    public Client register(@RequestBody Client client) {
        return service.registerClient(client);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody Client client) {
        service.logoutClient(client);
    }

    @GetMapping("get-locations/{clientId}")
    public List<Location> getLocations(@PathVariable int clientId) throws LocationNotFoundException {
      //  return service.getLocations(id);
        return service.getSubscriptionLocations(clientId);
    }

    @GetMapping("/search-location/{location}")
    public Location searchLocation(@PathVariable String location) throws LocationNotFoundException {
        return service.searchLocation(location);
    }


    @PostMapping("/add-location/{clientId}")
    public Location addLocationToSubscription(@RequestBody Integer clientId) throws LocationNotFoundException {
        return service.addLocationToSubscription(clientId);
    }


    @PostMapping("/remove-location/{clientId}")
    public void removeLocationFromSubscription(@RequestBody Integer clientId) throws LocationNotFoundException {
        service.removeLocationFromSubscription(clientId);
    }

    @GetMapping("/connection")
    public boolean con() {
        return true;
    }

}
