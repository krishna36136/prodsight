package com.krishna.Metric_Producer.service;

import com.krishna.Metric_Producer.model.MetricEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MetricProducerService {
	public MetricProducerService(KafkaTemplate<String, MetricEvent> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

    private KafkaTemplate<String, MetricEvent> kafkaTemplate;

    private static final String TOPIC = "metric-events";
    


	public void sendMetric(MetricEvent event) {

        kafkaTemplate.send(TOPIC, event);

        System.out.println("Metric sent to Kafka: " + event);
    }
}