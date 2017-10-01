package io.github.mattshen.dakit.datatypes;

import io.github.mattshen.dakit.utils.StringUtils;

import java.util.Arrays;

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
        if (Arrays.asList(Observatory.values()).contains(StringUtils.trim(s))){
            return Observatory.valueOf(StringUtils.trim(s));
        } else {
            return OTHER;
        }
    }

}
