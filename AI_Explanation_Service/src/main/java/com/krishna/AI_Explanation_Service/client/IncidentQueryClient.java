package com.krishna.AI_Explanation_Service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.krishna.AI_Explanation_Service.dto.IncidentDetailsDto;

@Service
public class IncidentQueryClient {

    @Value("${incident.service.url}")
    private String baseUrl;

    private final WebClient webClient;

    public IncidentQueryClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public IncidentDetailsDto getIncidentDetails(Long id) {
        return webClient.get()
                .uri(baseUrl + "/incidents/" + id + "/details")
                .retrieve()
                .bodyToMono(IncidentDetailsDto.class)
                .block();
    }
}