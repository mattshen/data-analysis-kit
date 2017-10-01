package io.github.mattshen.dakit.utils;

import io.github.mattshen.dakit.datatypes.Observation;
import io.github.mattshen.dakit.datatypes.Observatory;
import org.junit.Assert;
import org.junit.Test;


public class LineParseUtilsTest {

    @Test
    public void parse_normal() throws Exception {
        Observation result = LineParseUtils.parse("2017-01-22T22:57|30906253,38458084|-1|FR");
        Assert.assertNotNull(result);
    }

    @Test
    public void parse_empty_line() {
        Observation result = LineParseUtils.parse("    ");
        Assert.assertNull(result);
    }

    @Test
    public void parse_incorrect_number1_value() {
        Observation result = LineParseUtils.parse("2016-10-25T09:40|x,y|-37|FR");
        Assert.assertNull(result);
    }

    @Test
    public void parse_incorrect_number2_value() {
        Observation result = LineParseUtils.parse("2016-10-25T09:40|1,2|-37abc|FR");
        Assert.assertNull(result);
    }

    @Test
    public void parse_incorrect_observatory_value() {
        Observation result = LineParseUtils.parse("2016-10-25T09:40|1,2|-37|XYZ");
        Assert.assertEquals(Observatory.OTHER, result.getObservatory());
    }

}