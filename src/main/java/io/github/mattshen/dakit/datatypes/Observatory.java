package io.github.mattshen.dakit.datatypes;

import io.github.mattshen.dakit.utils.StringUtils;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Stream;

public enum Observatory {

    AU(TemperatureUnit.CELSIUS, DistanceUnit.KM),
    US(TemperatureUnit.FAHRENHEIT, DistanceUnit.MILE),
    FR(TemperatureUnit.KELVIN, DistanceUnit.M),
    OTHER(TemperatureUnit.KELVIN, DistanceUnit.KM);

    private TemperatureUnit tUnit;
    private DistanceUnit dUnit;

    Observatory(TemperatureUnit tUnit, DistanceUnit dUnit) {
        this.tUnit = tUnit;
        this.dUnit = dUnit;
    }

    public static Observatory getValue(String s) {
        Observatory retVal = OTHER;
        for(Observatory v : Observatory.values()) {
            if(v.name().equalsIgnoreCase(StringUtils.trim(s))) {
                retVal = v;
                break;
            }
        }
        return retVal;
    }

}
