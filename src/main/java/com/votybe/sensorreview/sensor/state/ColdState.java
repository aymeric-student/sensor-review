package com.votybe.sensorreview.sensor.state;

public class ColdState implements SensorState {
    @Override
    public String getStatus() {
        return SensorStateValue.COLD.toString();
    }
}
