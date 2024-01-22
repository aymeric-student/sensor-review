package com.votybe.sensorreview.sensor;

import com.votybe.sensorreview.sensor.dto.TemperatureRequest;
import com.votybe.sensorreview.sensor.dto.TemperatureResponse;
import com.votybe.sensorreview.sensor.services.TemperatureHistoryService;
import com.votybe.sensorreview.sensor.services.TemperatureService;
import com.votybe.sensorreview.sensor.state.SensorState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/temperature")
public class TemperatureController {

    private final TemperatureService temperatureService;
    private final TemperatureLimitsConfig temperatureLimitsConfig;
    private final TemperatureHistoryService temperatureHistoryService;

    public TemperatureController(TemperatureService temperatureService,
                                 TemperatureLimitsConfig temperatureLimitsConfig,
                                 TemperatureHistoryService temperatureHistoryService
    ) {
        this.temperatureService = temperatureService;
        this.temperatureLimitsConfig = temperatureLimitsConfig;
        this.temperatureHistoryService = temperatureHistoryService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<TemperatureRequest>> getTemperatureHistory() {
        List<TemperatureRequest> history = temperatureHistoryService.getTemperatureHistory();
        return ResponseEntity.ok(history);
    }

    @PostMapping("/limits")
    public ResponseEntity<String> updateTemperatureLimits(@RequestBody TemperatureLimitsConfig newLimits) {
        double newHot = newLimits.getHot();
        double newCold = newLimits.getCold();

        if (isValidTemperature(newHot) && isValidTemperature(newCold)) {
            temperatureLimitsConfig.setHot(newHot);
            temperatureLimitsConfig.setCold(newCold);
            return ResponseEntity.ok("Temperature limits updated successfully. 0-70");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid temperature limits provided.");
        }
    }

    private boolean isValidTemperature(double temperature) {
        return temperature >= 0.0 && temperature <= 60.0;
    }

    @GetMapping("/current")
    public ResponseEntity<TemperatureResponse> getTemperature() {
        double temperature = temperatureService.getCurrentTemperature();
        SensorState state = temperatureService.determineSensorState(temperature);
        TemperatureResponse response = new TemperatureResponse(temperature, state.getStatus());

        LocalDateTime timestamp = LocalDateTime.now();
        temperatureHistoryService.addTemperatureRequest(timestamp, temperature, state.getStatus());

        return ResponseEntity.ok(response);
    }
}


