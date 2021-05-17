package com.example.ALSA.weather.server.clients;

import javax.persistence.*;

@Entity // For Hibernate. Create JPA Entity.
@Table(name = "alsa_clients") // ORM: Mapping object to DB table.
public class Client {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Needed to auto increment id in DB.
    private int id;


    //TODO:  No args constructor. Needed for DB ORM
    //TODO:  All args constructor
    //TODO:  GETTERS

}
