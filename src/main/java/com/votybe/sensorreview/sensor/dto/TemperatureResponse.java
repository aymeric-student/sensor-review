package com.votybe.sensorreview.sensor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TemperatureResponse {
    private final double temperature;
    private final String status;
}