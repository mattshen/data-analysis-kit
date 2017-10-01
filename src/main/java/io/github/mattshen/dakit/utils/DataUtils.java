package io.github.mattshen.dakit.utils;

import io.github.mattshen.dakit.datatypes.Observation;
import io.github.mattshen.dakit.datatypes.Observatory;

import java.time.Instant;




public class DataUtils {

    public static interface Ranges {
        int MIN_X = 0;
        int MAX_X = 40_000_000;

        int MIN_Y = 0;
        int MAX_Y = 40_000_000;

        int MIN_TEMPERATURE = -50;
        int MAX_TEMPERATURE = 50;

        Instant START_DATE = Instant.parse("2015-09-30T00:00:00.00Z");
        Instant END_DATE = Instant.parse("2017-09-30T00:00:00.00Z");
    }


    public static int generateRandomInteger(int min, int max) {
        int diff = max - min + 1;
        int rand = min + (int) (Math.random() * diff);
        return rand;
    }

    public static long generateRandomLong(long min, long max) {
        long diff = max - min + 1;
        long rand = min + (long) (Math.random() * diff);
        return rand;
    }

    public static Instant generateRandomDateTime(Instant start, Instant end) {
        long randEpochMilli = generateRandomLong(start.toEpochMilli(), end.toEpochMilli());
        return Instant.ofEpochMilli(randEpochMilli);
    }

    public static int generateRandomX() {
        return generateRandomInteger(Ranges.MIN_X, Ranges.MAX_X);
    }

    public static int generateRandomY() {
        return generateRandomInteger(Ranges.MIN_Y, Ranges.MAX_Y);
    }

    public static int generateRandomTemperature() {
        return generateRandomInteger(Ranges.MIN_TEMPERATURE, Ranges.MAX_TEMPERATURE);
    }

    public static String generateRandomTimeString() {
        return generateRandomDateTime(Ranges.START_DATE, Ranges.END_DATE).toString().substring(0, 16);
    }

    public static Observatory selectRandomObservatory() {
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
