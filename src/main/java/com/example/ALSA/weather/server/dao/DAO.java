package com.example.ALSA.weather.server.dao;

import com.example.ALSA.weather.server.model.Client;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository("dao")
public class DAO implements ALSADatabase {
    private static DAO instance;

    public static DAO getInstance(){
        if(instance == null){
            instance = new DAO();
        }
        return instance;
    }

    @Override
    public int createClient(UUID id, Client client) {
        System.out.println("[TEST] - created client!");
        return 0;
    }

    @Override
    public int login(Client client) {
        System.out.println("[TEST] - client logged in!");
        return 0;
    }
}
