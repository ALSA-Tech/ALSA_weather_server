package com.example.ALSA.weather.server.locations.gsonClasses;

import java.util.List;

public class Parameters {
    String name;
    String levelType;
    String unit;
    List<Double> values;

    public Parameters(String name, String levelType, String unit, List<Double> values) {
        this.name = name;
        this.levelType = levelType;
        this.unit = unit;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", levelType='" + levelType + '\'' +
                ", unit='" + unit + '\'' +
                ", values=" + values +
                '}';
    }
}
