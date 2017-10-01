package io.github.mattshen.dakit.datatypes;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SummaryStatistics {

    private int count = 0;
    private int minTemperature = 0;
    private int maxTemperature = 0;
    private long temperatureSum = 0;
    private int meanTemperature = 0;

    private final Map<Observatory, Integer> observations = Collections.synchronizedMap(new HashMap<>());

    private Observation prevRecord = null;
    private long traveledDistance = 0;

    @Override
    public String toString() {
        return "Minimum Temperature (celsius): " + minTemperature +
                "\nMaximum Temperature (celsius): " + maxTemperature +
                "\nSum Temperature (celsius): " + temperatureSum +
                "\nMean Temperature (celsius): " + meanTemperature +
                "\nCount: " + count +
                "\nTraveledDistance (meters): " + traveledDistance +
                "\nObservations per post: " + observations;

    }

    public void add(Observation record) {

        if (this.count == 0) {
            this.minTemperature = record.getTemperatureInCelsius();
            this.maxTemperature = record.getTemperatureInCelsius();
        } else {
            this.minTemperature = Math.min(this.minTemperature, record.getTemperatureInCelsius());
            this.maxTemperature = Math.max(this.maxTemperature, record.getTemperatureInCelsius());
        }

        this.temperatureSum += record.getTemperatureInCelsius();
        this.observations.compute(record.getObservatory(), (observatory, value) -> value == null ? 1 : value + 1);

        //distance of two consecutive locations
        if (prevRecord == null) {
            prevRecord = record;
        } else {
            long a = prevRecord.getXInMeters() - record.getXInMeters();
            long b = prevRecord.getYInMeters() - record.getYInMeters();
            traveledDistance += Math.sqrt(a * a + b * b);
        }

        this.prevRecord = record;
        this.count++;

    }

    public SummaryStatistics done() {
        this.meanTemperature = (int) (this.temperatureSum / this.count);
        return this;
    }

    public int getCount() {
        return count;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public long getTemperatureSum() {
        return temperatureSum;
    }

    public int getMeanTemperature() {
        return meanTemperature;
    }

    public Map<Observatory, Integer> getObservations() {
        return observations;
    }

    public long getTraveledDistance() {
        return traveledDistance;
    }

    public void setTraveledDistance(long traveledDistance) {
        this.traveledDistance = traveledDistance;
    }
}
