package com.votybe.sensorreview.sensor.state;

public class HotState implements SensorState {
    @Override
    public String getStatus() {
        return SensorStateValue.HOT.toString();
    }
}
