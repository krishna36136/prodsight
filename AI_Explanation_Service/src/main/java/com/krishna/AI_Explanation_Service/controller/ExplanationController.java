package com.krishna.AI_Explanation_Service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.AI_Explanation_Service.dto.ExplanationResponseDto;
import com.krishna.AI_Explanation_Service.service.ExplanationService;

@RestController
@RequestMapping("/explanations")
public class ExplanationController {

    private final ExplanationService explanationService;

    public ExplanationController(ExplanationService explanationService) {
        this.explanationService = explanationService;
    }

    @GetMapping("/{incidentId}")
    public ExplanationResponseDto getExplanation(@PathVariable Long incidentId) {
        return explanationService.generateExplanation(incidentId);
    }
}
