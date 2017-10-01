package io.github.mattshen.dakit.datatypes;


import io.github.mattshen.dakit.utils.StringUtils;

import java.util.function.Function;

public enum DistanceUnit {

    KM(km -> km * 1000, m -> m / 1000),
    MILE(mi -> (int) (mi * 1609.34), m -> (int) (m / 1609.34)),
    M(m -> m, m -> m);

    public Function<Integer, Integer> toMeters;
    public Function<Integer, Integer> fromMeters;

    DistanceUnit(Function<Integer, Integer> toMeters, Function<Integer, Integer> fromMeters) {
        this.toMeters = toMeters;
        this.fromMeters = fromMeters;
    }

    public static DistanceUnit getValue(String s) {
        DistanceUnit retVal = M;
        for(DistanceUnit v : DistanceUnit.values()) {
            if(v.name().equalsIgnoreCase(StringUtils.trim(s))) {
                retVal = v;
                break;
            }
        }
        return retVal;
    }

}
