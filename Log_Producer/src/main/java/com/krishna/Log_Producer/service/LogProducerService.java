package com.krishna.Log_Producer.service;

import com.krishna.Log_Producer.model.LogEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogProducerService {

    private final KafkaTemplate<String, LogEvent> kafkaTemplate;

    public LogProducerService(KafkaTemplate<String, LogEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(LogEvent logEvent) {
        kafkaTemplate.send("log-events", logEvent);
        System.out.println("Log sent: " + logEvent.getMessage());
    }
}