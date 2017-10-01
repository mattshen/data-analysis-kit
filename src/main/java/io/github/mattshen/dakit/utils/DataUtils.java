package io.github.mattshen.dakit.utils;

import io.github.mattshen.dakit.datatypes.Observation;
import io.github.mattshen.dakit.datatypes.Observatory;

import java.time.Instant;




public class DataUtils {

    public interface Ranges {
        int MIN_X = 0;
        int MAX_X = 40_000_000;

        int MIN_Y = 0;
        int MAX_Y = 40_000_000;

        int MIN_TEMPERATURE = -50;
        int MAX_TEMPERATURE = 50;

        Instant START_DATE = Instant.parse("2015-09-30T00:00:00.00Z");
        Instant END_DATE = Instant.parse("2017-09-30T00:00:00.00Z");
    }


    private static int generateRandomInteger(int min, int max) {
        int diff = max - min + 1;
        return min + (int) (Math.random() * diff);
    }

    private static long generateRandomLong(long min, long max) {
        long diff = max - min + 1;
        return min + (long) (Math.random() * diff);
    }

    private static Instant generateRandomDateTime() {
        long randEpochMilli = generateRandomLong(Ranges.START_DATE.toEpochMilli(), Ranges.END_DATE.toEpochMilli());
        return Instant.ofEpochMilli(randEpochMilli);
    }

    private static int generateRandomX() {
        return generateRandomInteger(Ranges.MIN_X, Ranges.MAX_X);
    }

    private static int generateRandomY() {
        return generateRandomInteger(Ranges.MIN_Y, Ranges.MAX_Y);
    }

    private static int generateRandomTemperature() {
        return generateRandomInteger(Ranges.MIN_TEMPERATURE, Ranges.MAX_TEMPERATURE);
    }

    private static String generateRandomTimeString() {
        return generateRandomDateTime().toString().substring(0, 16);
    }

    private static Observatory selectRandomObservatory() {
        Observatory[] observatories = Observatory.values();
        int randIndex = generateRandomInteger(0, observatories.length - 1);
        return observatories[randIndex];
    }

    public static Observation generateDummyObservation() {
        Observation record = new Observation();
        record.setTimestamp(generateRandomTimeString());
        record.setX(generateRandomX());
        record.setY(generateRandomY());
        record.setTemperature(generateRandomTemperature());
        record.setObservatory(selectRandomObservatory());
        return record;
    }

}
