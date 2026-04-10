package com.krishna.AI_Explanation_Service.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OpenAIClient {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.url}")
    private String url;

    private final WebClient webClient;

    public OpenAIClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getExplanation(String prompt) {

        Map<String, Object> request = Map.of(
                "model", "llama-3.1-8b-instant",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                ),
                "temperature", 0.2
        );

        Map response = webClient.post()
                .uri(url)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List choices = (List) response.get("choices");
        Map choice = (Map) choices.get(0);
        Map message = (Map) choice.get("message");

        return (String) message.get("content");
    }
}