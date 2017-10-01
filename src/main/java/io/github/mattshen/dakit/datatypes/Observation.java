package io.github.mattshen.dakit.datatypes;

import java.text.MessageFormat;

public class Observation {

    private String timestamp;
    private int x;
    private int y;
    private int temperature;
    private Observatory observatory;

    public String toString() {
        return MessageFormat.format("{0}|{1},{2}|{3}|{4}", timestamp, String.valueOf(x), String.valueOf(y), temperature, observatory.name());
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getX() {
        return x;
    }

    public int getXInMeters() {
        return observatory.dUnit.toMeters.apply(x);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setXInMeter(int x) {
        this.x = observatory.dUnit.fromMeters.apply(x);
    }

    public int getY() {
        return y;
    }

    public int getYInMeters() {
        return observatory.dUnit.toMeters.apply(y);
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setYInMeters(int y) {
        this.y = observatory.dUnit.fromMeters.apply(y);
    }

    public int getTemperature() {
        return temperature;
    }

    public int getTemperatureInCelsius() {
        return observatory.tUnit.toCelsius.apply(temperature);
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setTemperatureInCelsius(int temperature) {
        this.temperature = observatory.tUnit.fromCelsius.apply(temperature);
    }

    public Observatory getObservatory() {
        return observatory;
    }

    public void setObservatory(Observatory observatory) {
        this.observatory = observatory;
    }

}
