package io.github.mattshen.dakit.datatypes;

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


}
