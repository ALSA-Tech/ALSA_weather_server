package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.locations.Location;
import com.example.ALSA.weather.server.locations.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@SessionAttributes({"user"})
public class ClientController {
//TODO naming convention
    private final ClientService service;

    @Autowired // Autowire (inject) ClientService from the Spring container.
    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Client login(@RequestBody Client client, HttpSession httpSession) throws ClientNotFoundException, EmailException{
        Client successfulClient =  service.loginClient(client);
        httpSession.setAttribute("user", client);
       return successfulClient;
    }

    @PostMapping("/register")
    public Client register(@RequestBody Client client) throws EmailException {
        return  service.registerClient(client);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody Client client, HttpSession httpSession) throws ClientNotFoundException {
        httpSession.invalidate();
        service.logoutClient(client);
    }

    @GetMapping("/get-locations/{clientId}")////<List<Location>>
    public ResponseEntity<List<?>> getLocations(@PathVariable int clientId, HttpSession httpSession) throws LocationNotFoundException, ClientNotFoundException {

        if(httpSession.getAttribute("user") == null){
            return new ResponseEntity(null,HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(service.getSubscriptionLocations(clientId),HttpStatus.OK);
    }

    @GetMapping("/search-location/{location}")
    public Location searchLocation(@PathVariable String location) throws LocationNotFoundException {
        return service.searchLocation(location);
    }

    @PostMapping("/update")// Client
    public ResponseEntity<?> updateClient(@RequestBody Client client,  HttpSession httpSession) throws ClientNotFoundException {
        // Typical usage: Add and remove locations from subscriptions.
        if(httpSession.getAttribute("user") == null){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
         }
        return new ResponseEntity<>(service.updateClient(client),HttpStatus.OK);
    }

    @GetMapping("/connection")
    public boolean con() {
        return true;
    }


}
