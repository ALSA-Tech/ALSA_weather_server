package com.example.ALSA.weather.server.locations;

/**
 * Model
 */

public class Location {

    private String location; // Name
    private String requestTimeStamp;
 //   private double[] maxTemps; // Max temp one per day. 10 days

    public Location(String location, String requestTimeStamp) {
        this.location = location;
        this.requestTimeStamp = requestTimeStamp;
    }

    public String getLocation() {
        return location;
    }

    public String getRequestTimeStamp() {
        return requestTimeStamp;
    }
}
