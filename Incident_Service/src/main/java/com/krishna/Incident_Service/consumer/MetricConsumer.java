package com.krishna.Incident_Service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.krishna.Incident_Service.model.MetricEvent;
import com.krishna.Incident_Service.rules.AnomalyDetector;
import com.krishna.Incident_Service.service.IncidentService;

@Component
public class MetricConsumer {

    private final IncidentService incidentService;
    private final AnomalyDetector anomalyDetector;
    
    public MetricConsumer(IncidentService incidentService, AnomalyDetector anomalyDetector) {
		super();
		this.incidentService = incidentService;
		this.anomalyDetector = anomalyDetector;
	}

    @KafkaListener(topics = "metric-events", groupId = "incident-group")
    public void consume(MetricEvent metricEvent) {

        System.out.println("Metric received: " + metricEvent);

        boolean errorAnomaly = anomalyDetector.detectErrorRateAnomaly(metricEvent);
        boolean latencyAnomaly = anomalyDetector.detectLatencyAnomaly(metricEvent);

        if (errorAnomaly) {

            incidentService.createIncident(
                    metricEvent.getServiceName(),
                    "HIGH_ERROR_RATE",
                    "HIGH"
            );

        } else if (anomalyDetector.isErrorRecovered(metricEvent.getServiceName())) {

            incidentService.resolveIncident(
                    metricEvent.getServiceName(),
                    "HIGH_ERROR_RATE"
            );
        }

        if (latencyAnomaly) {

            incidentService.createIncident(
                    metricEvent.getServiceName(),
                    "HIGH_LATENCY",
                    "MEDIUM"
            );

        } else if (anomalyDetector.isLatencyRecovered(metricEvent.getServiceName())) {

            incidentService.resolveIncident(
                    metricEvent.getServiceName(),
                    "HIGH_LATENCY"
            );
        }
    }
    
}