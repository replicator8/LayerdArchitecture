package org.example.domain;

public enum TemperatureMode {
    FROZEN,
    CHILLED,
    AT_ROOM_TEMPERATURE;

    public static TemperatureMode getRandom() {
        double a = Math.random();
        if (a % 10 == 0) {
            return CHILLED;
        } else if (a % 10 == 1) {
            return AT_ROOM_TEMPERATURE;
        }
        return FROZEN;
    }
}
