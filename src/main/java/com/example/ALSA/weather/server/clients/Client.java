package com.example.ALSA.weather.server.clients;

import com.example.ALSA.weather.server.utils.StringListConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // For Hibernate. Create JPA Entity.
@Table(name = "alsa_clients") // ORM: Mapping object to DB table.
public class Client {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Needed to auto increment id in DB.
    private int id;
    private String name;
    private String password;
    private String email;

    // ORM: Persist property type List<String> as String
    @Convert(converter = StringListConverter.class)
    @Column(name = "subscriptions")
    private List<String> locationSubscriptions;

    // No args constructor. Needed for DB ORM
    public Client (){}

    // All args constructor
    public Client(int id, String name, String password, String email, List<String> locationSubscriptions) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.locationSubscriptions = locationSubscriptions;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getLocationSubscriptions() {
        return locationSubscriptions;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", locationSubscriptions=" + locationSubscriptions +
                '}';
    }

}