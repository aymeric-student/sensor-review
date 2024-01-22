package com.votybe.sensorreview.sensor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class TemperatureLimitsConfig {
    double hot = Constants.HOT;
    double cold = Constants.COLD;
}

