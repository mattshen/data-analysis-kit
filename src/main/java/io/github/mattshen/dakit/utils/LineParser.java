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
public class LineParser {

    private static Logger LOG = LoggerFactory.getLogger(LineParser.class);

    public static Observation parse(String line) {
        Observation record = null;
        try {
            String[] values = line.split("\\|");
            if (values.length == 4) {
                String[] xy = values[1].split(",");
                if (xy.length == 2) {
                    record = new Observation();
                    record.setX(Integer.valueOf(xy[0]));
                    record.setY(Integer.valueOf(xy[1]));
                    record.setTimestamp(values[0]);
                    record.setTemperature(Integer.valueOf(values[2]));
                    record.setObservatory(Observatory.valueOf(values[3]));
                }
            }

        } catch (Exception e) {
            LOG.warn("Warnings", e);
        }
        return record;

    }

}
