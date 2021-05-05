package com.example.ALSA.weather.server.service;

import com.example.ALSA.weather.server.dao.ALSADatabase;
import com.example.ALSA.weather.server.dao.DAO;
import com.example.ALSA.weather.server.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ALSADatabase alsaDatabase;

    @Autowired
    public ClientService(@Qualifier("dao") ALSADatabase alsaDatabase){
        this.alsaDatabase = alsaDatabase;
    }

    public int createClient(Client client){
        return alsaDatabase.createClient(client);
    }

    public int login(Client client){
        return alsaDatabase.login(client);
    }

}
