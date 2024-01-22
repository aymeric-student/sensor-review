package com.votybe.sensorreview.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TemperatureUtils {

    public static double roundByPrecision(double value, int decimalPlaces) {
        return BigDecimal.valueOf(value)
                .setScale(decimalPlaces, RoundingMode.HALF_UP)
                .doubleValue();
    }
}