package com.votybe.sensorreview.sensor.services;

import com.votybe.sensorreview.sensor.state.SensorStateFactory;
import com.votybe.sensorreview.sensor.TemperatureGenerator;
import com.votybe.sensorreview.sensor.state.SensorState;
import org.springframework.stereotype.Service;
import com.votybe.sensorreview.utils.TemperatureUtils;

@Service
public class TemperatureService {
    private final TemperatureGenerator temperatureGenerator;
    private final SensorStateFactory sensorStateFactory;

    public TemperatureService(TemperatureGenerator temperatureGenerator,
                              SensorStateFactory sensorStateFactory
    ) {
        this.temperatureGenerator = temperatureGenerator;
        this.sensorStateFactory = sensorStateFactory;
    }

    public double getCurrentTemperature() {
        double temperature = temperatureGenerator.generateTemperature();
        final int precision = 2;
        return TemperatureUtils.roundByPrecision(temperature, 2);
    }

    public SensorState determineSensorState(double temperature) {
        return sensorStateFactory.getSensorState(temperature);
    }
}
