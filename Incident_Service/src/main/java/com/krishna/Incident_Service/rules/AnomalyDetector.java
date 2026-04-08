package com.krishna.Incident_Service.rules;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.krishna.Incident_Service.model.MetricEvent;

@Component
public class AnomalyDetector {

    private static final int WINDOW_SIZE = 5;
    private static final double ERROR_THRESHOLD = 5.0;
    private static final double LATENCY_THRESHOLD = 500;

    private Map<String, Deque<MetricEvent>> serviceWindows = new HashMap<>();

    public boolean detectErrorRateAnomaly(MetricEvent metric) {

        Deque<MetricEvent> window = getWindow(metric.getServiceName());

        updateWindow(window, metric);

        if (window.size() < WINDOW_SIZE) {
            return false;
        }

        long highErrorCount = window.stream()
                .filter(m -> m.getErrorRate() > ERROR_THRESHOLD)
                .count();

        return highErrorCount >= 3;
    }

    public boolean detectLatencyAnomaly(MetricEvent metric) {

        Deque<MetricEvent> window = getWindow(metric.getServiceName());

        updateWindow(window, metric);

        if (window.size() < WINDOW_SIZE) {
            return false;
        }

        long highLatencyCount = window.stream()
                .filter(m -> m.getAvgLatency() > LATENCY_THRESHOLD)
                .count();

        return highLatencyCount >= 3;
    }
    
    public boolean isErrorRecovered(String serviceName) {

        Deque<MetricEvent> window = serviceWindows.get(serviceName);

        if (window == null || window.size() < WINDOW_SIZE) {
            return false;
        }

        return window.stream()
                .allMatch(m -> m.getErrorRate() < ERROR_THRESHOLD);
    }

    public boolean isLatencyRecovered(String serviceName) {

        Deque<MetricEvent> window = serviceWindows.get(serviceName);

        if (window == null || window.size() < WINDOW_SIZE) {
            return false;
        }

        return window.stream()
                .allMatch(m -> m.getAvgLatency() < LATENCY_THRESHOLD);
    }

    private Deque<MetricEvent> getWindow(String serviceName) {
        serviceWindows.putIfAbsent(serviceName, new LinkedList<>());
        return serviceWindows.get(serviceName);
    }

    private void updateWindow(Deque<MetricEvent> window, MetricEvent metric) {

        window.addLast(metric);

        if (window.size() > WINDOW_SIZE) {
            window.removeFirst();
        }
    }
}