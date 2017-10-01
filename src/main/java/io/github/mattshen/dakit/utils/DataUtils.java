package io.github.mattshen.dakit.utils;

import io.github.mattshen.dakit.datatypes.DistanceUnit;
import io.github.mattshen.dakit.datatypes.Observation;
import io.github.mattshen.dakit.datatypes.Observatory;
import io.github.mattshen.dakit.datatypes.TemperatureUnit;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.function.Function;


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

    private static int generateRandomXInMeters() {
        return generateRandomInteger(Ranges.MIN_X, Ranges.MAX_X);
    }

    private static int generateRandomYInMeters() {
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

        Observatory observatory = selectRandomObservatory();

        Observation record = new Observation();
        record.setTimestamp(generateRandomTimeString());
        record.setObservatory(observatory);

        record.setXInMeter(generateRandomXInMeters());
        record.setYInMeters(generateRandomYInMeters());
        record.setTemperatureInCelsius(generateRandomTemperature());

        return record;
    }

    public static String normalize(Observation record, DistanceUnit dUnit, TemperatureUnit tUnit) {

        Function<Integer, Integer> distanceConverter = dUnit.fromMeters.compose(record.getObservatory().dUnit.toMeters);
        Function<Integer, Integer> temperatureConverter = tUnit.fromCelsius.compose(record.getObservatory().tUnit.toCelsius);

        return MessageFormat.format("{0}|{1},{2}|{3}|{4}",
                record.getTimestamp(),
                String.valueOf(distanceConverter.apply(record.getX())),
                String.valueOf(distanceConverter.apply(record.getY())),
                temperatureConverter.apply(record.getTemperature()),
                record.getObservatory().name());

    }


}
