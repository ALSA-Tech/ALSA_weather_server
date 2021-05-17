package com.example.ALSA.weather.server.spring_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/example/clients")
public class X_ClientController {

    private final X_ClientService service;

    @Autowired // Autowire (inject) ClientService from the Spring container.
    public X_ClientController(X_ClientService service) {
        this.service = service;
    }

    @GetMapping()
    public Iterable<X_Client> getClients() {
        return service.getClients();
    }

    @GetMapping("/{id}")
    public X_Client getClient(@PathVariable Integer id) throws X_ClientNotFoundException {
        return service.getClient(id);
    }

    @PostMapping("/register")
    public X_Client addClient(@RequestBody X_Client newClient) {
        return service.addClient(newClient);
    }

    @PostMapping("/update")
    public X_Client updateClient(@RequestBody X_Client updatedClient) {
        return service.updateClient(updatedClient);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id) {
        service.deleteClient(id);
    }


}
