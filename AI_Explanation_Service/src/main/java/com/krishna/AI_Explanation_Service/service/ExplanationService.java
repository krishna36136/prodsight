package com.krishna.AI_Explanation_Service.service;

import org.springframework.stereotype.Service;

import com.krishna.AI_Explanation_Service.client.IncidentQueryClient;
import com.krishna.AI_Explanation_Service.client.OpenAIClient;
import com.krishna.AI_Explanation_Service.dto.ExplanationResponseDto;
import com.krishna.AI_Explanation_Service.dto.IncidentDetailsDto;
import com.krishna.AI_Explanation_Service.dto.IncidentResponseDto;
import com.krishna.AI_Explanation_Service.dto.LogResponseDto;

import tools.jackson.databind.ObjectMapper;

@Service
public class ExplanationService {

    private final IncidentQueryClient incidentClient;
    private final OpenAIClient openAIClient;

    public ExplanationService(IncidentQueryClient incidentClient,
                              OpenAIClient openAIClient) {
        this.incidentClient = incidentClient;
        this.openAIClient = openAIClient;
    }

    public ExplanationResponseDto generateExplanation(Long incidentId) {

        IncidentDetailsDto details = incidentClient.getIncidentDetails(incidentId);

        String prompt = buildPrompt(details);

        String aiResponse = openAIClient.getExplanation(prompt);

        return parseResponse(aiResponse);
    }

    private String buildPrompt(IncidentDetailsDto details) {

        StringBuilder sb = new StringBuilder();

        sb.append("""
    You are an observability AI system.

    ROLE & SCOPE:
    - You are an external analyzer.
    - You are NOT part of the system being analyzed.
    - Service names are identifiers only and must NOT be assumed as root causes.

    IMPORTANT INSTRUCTIONS:
    - Return ONLY valid JSON
    - Do NOT include explanation text outside JSON
    - Do NOT include markdown (no ```json)
    - Output must be directly parsable JSON

    Required JSON format:
    {
      "summary": "...",
      "probableCause": "...",
      "recommendation": "..."
    }

    ====================
    INCIDENT DETAILS
    ====================
    """);

        IncidentResponseDto incident = details.getIncident();

        sb.append("Incident ID: ").append(incident.getId()).append("\n");
        sb.append("Service Name (context only): ")
          .append(incident.getServiceName()).append("\n");
        sb.append("Incident Type: ")
          .append(incident.getIncidentType()).append("\n");
        sb.append("Severity: ")
          .append(incident.getSeverity()).append("\n");
        sb.append("Status: ")
          .append(incident.getStatus()).append("\n");
        sb.append("Start Time (epoch): ")
          .append(incident.getStartTime()).append("\n");
        sb.append("End Time (epoch): ")
          .append(incident.getEndTime()).append("\n\n");

        sb.append("""
    ====================
    LOG ENTRIES
    ====================
    """);

        for (LogResponseDto log : details.getLogs()) {
            sb.append("Timestamp (epoch): ").append(log.getTimestamp()).append("\n");
            sb.append("Log Level: ").append(log.getLevel()).append("\n");
            sb.append("Service Name (context only): ")
              .append(log.getServiceName()).append("\n");
            sb.append("Message: ").append(log.getMessage()).append("\n");
            sb.append("--------------------------------\n");
        }

        return sb.toString();
    }
    
    private String extractJson(String response) {
        int start = response.indexOf("{");
        int end = response.lastIndexOf("}");
        return response.substring(start, end + 1);
    }

    private ExplanationResponseDto parseResponse(String aiResponse) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            String cleanJson = extractJson(aiResponse);

            return mapper.readValue(cleanJson, ExplanationResponseDto.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response: " + aiResponse, e);
        }
    }
}
