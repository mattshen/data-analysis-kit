package io.github.mattshen.dakit;

import io.github.mattshen.dakit.datatypes.Observation;
import io.github.mattshen.dakit.datatypes.Observatory;
import io.github.mattshen.dakit.utils.DataUtils;
import org.junit.Test;

import java.time.Instant;
import java.util.stream.IntStream;

/**
 * Created by zhongweishen on 29/9/17.
 */
public class DataUtilsTest {


    @Test
    public void test() {
      Integer a1 = Integer.MAX_VALUE;

      Integer a2 = 5;
        System.out.println(a1);
      long a3 = 0;
      a3 += a1;
      a3 += a2;
        System.out.println(a3);
    }

    //@Test
    public void generateDummyGPSRecord() throws Exception {
        IntStream.range(0, 10000).forEach(i -> {
            Observation record = DataUtils.generateDummyGPSRecord();
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