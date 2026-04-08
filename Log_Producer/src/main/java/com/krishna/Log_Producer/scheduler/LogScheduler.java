package com.krishna.Log_Producer.scheduler;

import com.krishna.Log_Producer.model.LogEvent;
import com.krishna.Log_Producer.service.LogProducerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LogScheduler {

    private final LogProducerService logProducerService;
    private final Random random = new Random();

    private final String[] services = {
            "auth-service",
            "payment-service",
            "order-service",
            "inventory-service"
    };

    private final String[] levels = {
            "INFO", "WARN", "ERROR"
    };

    private final String[] messages = {
            "User login successful",
            "Database connection timeout",
            "Payment processing failed",
            "Order created successfully",
            "Inventory level low",
            "Cache miss occurred",
            "Service responded with delay"
    };

    public LogScheduler(LogProducerService logProducerService) {
        this.logProducerService = logProducerService;
    }

    @Scheduled(fixedRate = 2000)
    public void generateLogs() {

        String service = services[random.nextInt(services.length)];
        String level = levels[random.nextInt(levels.length)];
        String message = messages[random.nextInt(messages.length)];

        LogEvent logEvent = new LogEvent(
                service,
                level,
                message,
                System.currentTimeMillis()
        );

        logProducerService.sendLog(logEvent);
    }
}