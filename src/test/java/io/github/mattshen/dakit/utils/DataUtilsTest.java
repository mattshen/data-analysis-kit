package io.github.mattshen.dakit.utils;

import io.github.mattshen.dakit.datatypes.Observation;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;


public class DataUtilsTest {
    @Test
    public void generateDummyObservation() throws Exception {

        //dummy data should be able to be parsed
        IntStream.range(0, 10000).forEach( i -> {
            Observation generated = DataUtils.generateDummyObservation();
            Observation parsed = LineParseUtils.parse(generated.toString());

            Assert.assertEquals(generated.getObservatory(), parsed.getObservatory());
            Assert.assertEquals(generated.getTemperature(), parsed.getTemperature());
            Assert.assertEquals(generated.getTimestamp(), parsed.getTimestamp());
            Assert.assertEquals(generated.getX(), parsed.getX());
            Assert.assertEquals(generated.getY(), parsed.getY());

        });
    }

}