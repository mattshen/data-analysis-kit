package io.github.mattshen.dakit.utils;

import io.github.mattshen.dakit.datatypes.Observation;
import io.github.mattshen.dakit.datatypes.Observatory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Line parser for following exact format:
 * <p>
 * <timestamp>|<location: x, y>|<temperature>|<observatory>
 */
public class LineParseUtils {

    public static Observation parse(String line) {
        Observation record = null;
        String[] values = line.split("\\|");
        if (values.length == 4) {
            String[] xy = values[1].split(",");
            if (xy.length == 2 && checkNumericValues(values, xy)) {
                record = new Observation();
                record.setX(Integer.valueOf(StringUtils.trim(xy[0])));
                record.setY(Integer.valueOf(StringUtils.trim(xy[1])));
                record.setTimestamp(StringUtils.trim(values[0]));
                record.setTemperature(Integer.valueOf(StringUtils.trim(values[2])));
                record.setObservatory(Observatory.getValue(StringUtils.trim(values[3])));
            }
        }

        return record;
    }

    private static boolean checkNumericValues(String[] values, String[] xy) {
        return StringUtils.isNumber(xy[0]) && StringUtils.isNumber(xy[1]) && StringUtils.isNumber(values[2]);
    }

}
