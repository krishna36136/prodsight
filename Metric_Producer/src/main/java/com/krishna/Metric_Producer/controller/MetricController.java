package com.krishna.Metric_Producer.controller;

import com.krishna.Metric_Producer.model.MetricEvent;
import com.krishna.Metric_Producer.service.MetricProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metrics")
public class MetricController {

    private MetricProducerService producerService;

    @PostMapping
    public String sendMetric(@RequestBody MetricEvent event) {

        producerService.sendMetric(event);

        return "Metric sent successfully";
    }
}
