package com.krishna.Metric_Producer.scheduler;

import com.krishna.Metric_Producer.model.MetricEvent;
import com.krishna.Metric_Producer.service.MetricProducerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MetricScheduler {

    public MetricScheduler(MetricProducerService producerService) {
		super();
		this.producerService = producerService;
	}

	// ✅ Spring will inject this
    private final MetricProducerService producerService;

    // ✅ Initialized
    private final Random random = new Random();

    private final String[] services = {
    		"auth-service",
            "payment-service",
            "order-service",
            "inventory-service"
    };

    @Scheduled(fixedRate = 5000)
    public void generateMetrics() {

        String serviceName = services[random.nextInt(services.length)];

        double errorRate = random.nextDouble() * 10;
        double latency = 100 + random.nextDouble() * 900;
        int requestCount = 50 + random.nextInt(200);

        MetricEvent event = new MetricEvent(
                serviceName,
                System.currentTimeMillis(),
                errorRate,
                latency,
                requestCount
        );

        producerService.sendMetric(event);
    }
}