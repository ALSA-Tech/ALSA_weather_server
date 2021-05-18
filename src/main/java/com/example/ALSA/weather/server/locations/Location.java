package com.example.ALSA.weather.server.locations;

import java.util.ArrayList;

/**
 * Model
 */

public class Location {

    private String location; // Name
    private String requestTimeStamp;
    private ArrayList<LocationDataXY> dataSeriesXY;

 //   private double[] maxTemps; // Max temp one per day. 10 days


    public Location() {
    }

    public Location(String location, String requestTimeStamp, ArrayList<LocationDataXY> dataSeriesXY) {
        this.location = location;
        this.requestTimeStamp = requestTimeStamp;
        this.dataSeriesXY = dataSeriesXY;
    }

    public String getLocation() {
        return location;
    }

    public String getRequestTimeStamp() {
        return requestTimeStamp;
    }

    public ArrayList<LocationDataXY> getDataSeriesXY() {
        return dataSeriesXY;
    }

    @Override
    public String toString() {
        return "Location{" +
                "location='" + location + '\'' +
                ", requestTimeStamp='" + requestTimeStamp + '\'' +
                ", dataSeriesXY=" + dataSeriesXY +
                '}';
    }
}
