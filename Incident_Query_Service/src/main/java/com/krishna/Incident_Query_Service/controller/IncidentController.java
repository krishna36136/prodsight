package com.krishna.Incident_Query_Service.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.Incident_Query_Service.DTO.IncidentDetailsDto;
import com.krishna.Incident_Query_Service.DTO.IncidentResponseDto;
import com.krishna.Incident_Query_Service.DTO.LogResponseDto;
import com.krishna.Incident_Query_Service.service.IncidentService;

@RestController
@RequestMapping("/incidents")
@CrossOrigin(origins = "*")
public class IncidentController {

    private final IncidentService incidentService;
    
    

    public IncidentController(IncidentService incidentService) {
		super();
		this.incidentService = incidentService;
	}

	@GetMapping
    public Page<IncidentResponseDto> getAllIncidents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return incidentService.getAllIncidents(page, size);
    }

    @GetMapping("/{id}")
    public IncidentResponseDto getIncident(@PathVariable Long id) {
        return incidentService.getIncidentById(id);
    }

    @GetMapping("/{id}/logs")
    public List<LogResponseDto> getLogs(@PathVariable Long id) {
        return incidentService.getLogsForIncident(id);
    }

    @GetMapping("/{id}/details")
    public IncidentDetailsDto getDetails(@PathVariable Long id) {
        return incidentService.getIncidentDetails(id);
    }
}
