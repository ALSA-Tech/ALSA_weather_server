package com.example.ALSA.weather.server.clients;

import javax.persistence.*;

@Entity // For Hibernate. Create JPA Entity.
@Table(name = "alsa_clients") // ORM: Mapping object to DB table.
public class Client {
//Todo client variables
//TODO:  No args constructor. Needed for DB ORM
//TODO:  All args constructor
//TODO:  GETTERS

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Needed to auto increment id in DB.

    private int id; // string id  instead?
    private String name;
    private String password;

    public Client (){
    }

    public Client(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
