package io.github.mattshen.dakit;

import io.github.mattshen.dakit.datatypes.Observation;
import io.github.mattshen.dakit.datatypes.Observatory;
import io.github.mattshen.dakit.utils.DataUtils;

import java.time.Instant;
import java.util.stream.IntStream;

public class DataUtilsTest {


    //@Test
    public void generateDummyGPSRecord() throws Exception {
        IntStream.range(0, 10000).forEach(i -> {
            Observation record = DataUtils.generateDummyObservation();
            System.out.println(record);
        });
    }

    //@Test
    public void generateRandomTimeString() throws Exception {
        IntStream.range(0, 10000).forEach(i -> {
            String ts = DataUtils.generateRandomTimeString();
            System.out.println(ts);
        });
    }

    //@Test
    public void selectRandomObservatory() throws Exception {
        IntStream.range(0, 10000).forEach(i -> {
            Observatory observatory = DataUtils.selectRandomObservatory();

            System.out.println(observatory);
        });
    }

    //@Test
    public void generateRandomDateTime() throws Exception {
        IntStream.range(0, 10000).forEach(i -> {
            Instant datetime = DataUtils.generateRandomDateTime(DataUtils.Ranges.START_DATE, DataUtils.Ranges.END_DATE);
            System.out.println(datetime);
        });

    }

}