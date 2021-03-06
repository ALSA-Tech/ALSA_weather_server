package com.example.ALSA.weather.server.locations;

import java.time.LocalDate;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class LocationDataXY {

    private String localDate; //2021-05-18
    private double temp; //Max temp



    public LocationDataXY() {
    }

    public LocationDataXY(String localDate, double temp) {
        this.localDate = localDate;
        this.temp = temp;
    }

    public String getLocalDate() {
        return localDate;
    }

    public Double getTemp() {
        return temp;
    }
    @Override
    public String toString() {
        return "LocationDataXY{" +
                "localDate=" + localDate +
                ", temp=" + temp +
                '}';
    }
}
