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

    private Map<Observatory, Integer> observations = Collections.synchronizedMap(new HashMap<>());

    private Observation prevRecord = null;
    private long traveledDistance = 0;

    @Override
    public String toString() {
        return "Minimum Temperature: " + minTemperature +
                "\nMaximum Temperature: " + maxTemperature +
                "\nSum Temperature: " + temperatureSum +
                "\nMean Temperature: " + meanTemperature +
                "\nCount: " + count +
                "\nTraveledDistance: " + traveledDistance + " metres" +
                "\nObservations per post: " + observations;

    }

    public SummaryStatistics add(Observation record) {

        if (this.count == 0) {
            this.minTemperature = record.getTemperature();
            this.maxTemperature = record.getTemperature();
        } else {
            this.minTemperature = Math.min(this.minTemperature, record.getTemperature());
            this.maxTemperature = Math.max(this.maxTemperature, record.getTemperature());
        }

        this.temperatureSum += record.getTemperature();
        this.observations.compute(record.getObservatory(), (observatory, value) -> value == null ? 1 : value + 1);

        //distance of two consecutive locations
        if (prevRecord == null) {
            prevRecord = record;
        } else {
            long a = prevRecord.getX() - record.getX();
            long b = prevRecord.getY() - record.getY();
            traveledDistance += Math.sqrt(a * 2 + b * b);
        }

        this.prevRecord = record;
        this.count++;

        return this;

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
