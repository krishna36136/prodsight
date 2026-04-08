package com.krishna.Log_Ingestion_Service.consumer;

import com.krishna.Log_Ingestion_Service.model.LogEvent;
import com.krishna.Log_Ingestion_Service.repository.LogRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LogConsumer {

    private final LogRepository logRepository;

    public LogConsumer(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @KafkaListener(topics = "log-events", groupId = "log-group")
    public void consume(LogEvent logEvent) {

        logRepository.save(logEvent);

        System.out.println("Log stored in Elasticsearch: " + logEvent);
    }
}