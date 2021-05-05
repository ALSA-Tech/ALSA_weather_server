package com.example.ALSA.weather.server.api;

import com.example.ALSA.weather.server.model.Client;
import com.example.ALSA.weather.server.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/client")
@RestController
public class ClientController {
    //reference to service
    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    public void createClient (@RequestBody Client client){
        clientService.createClient(client);
    }

    @PostMapping(path = "/login")
    public void login(@RequestBody Client client){
         clientService.login(client);
    }

}
