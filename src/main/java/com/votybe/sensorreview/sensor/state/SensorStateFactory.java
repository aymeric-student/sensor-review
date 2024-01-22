package com.votybe.sensorreview.sensor.state;

import com.votybe.sensorreview.sensor.TemperatureLimitsConfig;
import org.springframework.stereotype.Component;

@Component
public class SensorStateFactory {
    private final TemperatureLimitsConfig temperatureLimitsConfig;

    public SensorStateFactory(TemperatureLimitsConfig temperatureLimitsConfig) {
        this.temperatureLimitsConfig = temperatureLimitsConfig;
    }

    public SensorState getSensorState(double temperature) {
        double hotLimit = temperatureLimitsConfig.getHot();
        double coldLimit = temperatureLimitsConfig.getCold();

        if (temperature >= hotLimit) {
            return new HotState();
        } else if (temperature < coldLimit) {
            return new ColdState();
        } else {
            return new WarmState();
        }
    }
}
