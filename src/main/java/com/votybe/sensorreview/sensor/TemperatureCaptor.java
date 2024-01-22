package com.votybe.sensorreview.sensor;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class TemperatureCaptor implements TemperatureGenerator {
    private final Random random = new Random();

    @Override
    public double generateTemperature() {
        return Constants.MIN_TEMPERATURE + (Constants.MAX_TEMPERATURE - Constants.MIN_TEMPERATURE) * random.nextDouble();
    }
}
