package com.votybe.sensorreview.sensor.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TemperatureRequest {
    private LocalDateTime timestamp;
    private double temperature;
    private String status;
}

