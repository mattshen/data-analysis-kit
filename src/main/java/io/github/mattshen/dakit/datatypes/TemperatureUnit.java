package io.github.mattshen.dakit.datatypes;


import io.github.mattshen.dakit.utils.StringUtils;

import java.util.function.Function;

public enum TemperatureUnit {

    CELSIUS(c -> c, c -> c),
    FAHRENHEIT(f -> (int) ((f - 32) / 1.8), c -> (int) (c * 1.8 + 32)),
    KELVIN(k -> (int) (k - 273.15), c -> (int) (c + 273.15));

    public final Function<Integer, Integer> toCelsius;
    public final Function<Integer, Integer> fromCelsius;

    TemperatureUnit(Function<Integer, Integer> toCelsius, Function<Integer, Integer> fromCelsius) {
        this.toCelsius = toCelsius;
        this.fromCelsius = fromCelsius;
    }

    public static TemperatureUnit getValue(String s) {
        TemperatureUnit retVal = CELSIUS;
        for(TemperatureUnit v : TemperatureUnit.values()) {
            if(v.name().equalsIgnoreCase(StringUtils.trim(s))) {
                retVal = v;
                break;
            }
        }
        return retVal;
    }

}
