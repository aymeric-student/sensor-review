package com.votybe.sensorreview.sensor.services;

import com.votybe.sensorreview.sensor.dto.TemperatureRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class TemperatureHistoryService {
    private final LinkedList<TemperatureRequest> requestHistory = new LinkedList<>();
    private static final int MAX_HISTORY_SIZE = 15;

    public void addTemperatureRequest(LocalDateTime timestamp, double temperature, String status) {
        TemperatureRequest request = createTemperatureRequest(timestamp, temperature, status);
        requestHistory.addFirst(request);
        checkHistorySize();
    }

    public List<TemperatureRequest> getTemperatureHistory() {
        return requestHistory;
    }

    private TemperatureRequest createTemperatureRequest(LocalDateTime timestamp, double temperature, String status) {
        TemperatureRequest request = new TemperatureRequest();
        request.setTimestamp(timestamp);
        request.setTemperature(temperature);
        request.setStatus(status);
        return request;
    }

    private void checkHistorySize() {
        if (requestHistory.size() > MAX_HISTORY_SIZE) {
            requestHistory.removeLast();
        }
    }
}
