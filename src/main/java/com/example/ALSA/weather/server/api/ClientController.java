package com.example.ALSA.weather.server.api;

import com.example.ALSA.weather.server.model.Client;
import com.example.ALSA.weather.server.model.ClientRequest;
import com.example.ALSA.weather.server.service.ClientService;
import com.example.ALSA.weather.server.utils.RSACrypto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("api/client")
@RestController
public class ClientController {
    //reference to service
    private final ClientService clientService;
    private final RSACrypto crypto;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        this.crypto = new RSACrypto();
        this.crypto.generateRSAKeys();
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    public void createClient(HttpEntity<String> httpEntity) {
        System.out.println();
       // String data = decryptClientRequest(httpEntity.getBody());
        Client client = new Gson().fromJson(httpEntity.getBody(), Client.class);
        clientService.createClient(client);
    }

/*
    @PostMapping
    public void createClient(@RequestBody Client client) {
        clientService.createClient(client);
    }
*/

    @PostMapping(path = "/login")
    public void login(@RequestBody Client client) {
        clientService.login(client);
    }

    // http://localhost:8080/api/client/test
    // Better and more control of data
    @RequestMapping(value = "/test", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public String testJson(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        // json contains the plain json string
        System.out.println(json);
        return "Bla bla";
    }

    @RequestMapping(value = "/voidtest", method = POST, consumes = APPLICATION_JSON_VALUE)
    public void voidJson(HttpEntity<String> httpEntity) {
        System.out.println(decryptClientRequest(httpEntity.getBody()));

    }


    // private GenerateKeys generateKeys ;
    @RequestMapping(value = "/key", method = GET, produces = APPLICATION_JSON_VALUE)
    public String key() {
        return crypto.publicKeyToString(crypto.getPublicKey());

    }

    private String decryptClientRequest(String body) {
        ClientRequest request = new Gson().fromJson(body, ClientRequest.class);
        String encryptedData = request.getData();
        System.out.println(encryptedData);
        System.out.println(encryptedData);
        String decrypt = crypto.decrypt(crypto.getPrivateKey(), encryptedData);
        System.out.println(decrypt);
        return decrypt;
    }

}
