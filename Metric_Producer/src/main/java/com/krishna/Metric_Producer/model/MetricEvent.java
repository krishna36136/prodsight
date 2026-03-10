package com.krishna.Metric_Producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricEvent {
    private String serviceName;
    private long timestamp;
    private double errorRate;
    private double avgLatency;
    private int requestCount;
}
