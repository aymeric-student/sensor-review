package com.votybe.sensorreview.sensor.state;

public class WarmState implements SensorState {
    @Override
    public String getStatus() {
        return SensorStateValue.WARM.toString();
    }
}