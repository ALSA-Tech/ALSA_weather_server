package com.example.ALSA.weather.server.spring_example;

import javax.persistence.*;

@Entity // For Hibernate. Create JPA Entity.
@Table(name = "alsa_clients") // ORM: Mapping object to DB table.
public class X_Client {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Needed to auto increment id in DB.
    private int id;

    private String name;
    private String password;

    // No args constructor. Needed for DB ORM
    public X_Client() {}

    // All args constructor
    public X_Client(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    @Override
    public String toString() {
        return "X_Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
