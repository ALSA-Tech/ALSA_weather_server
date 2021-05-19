package com.example.ALSA.weather.server.locations.gsonClasses;

import java.util.List;

public class TimeSeries {
    String validTime;
    List<Parameters> parameters;

    public TimeSeries(String validTime, List<Parameters> parameters) {
        this.validTime = validTime;
        this.parameters = parameters;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public List<Parameters> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameters> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "TimeSeries{" +
                "validTime='" + validTime + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
