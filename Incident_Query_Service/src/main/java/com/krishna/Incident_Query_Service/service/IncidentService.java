package com.krishna.Incident_Query_Service.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.krishna.Incident_Query_Service.DTO.IncidentDetailsDto;
import com.krishna.Incident_Query_Service.DTO.IncidentResponseDto;
import com.krishna.Incident_Query_Service.DTO.LogResponseDto;

public interface IncidentService {

    Page<IncidentResponseDto> getAllIncidents(int page, int size);

    IncidentResponseDto getIncidentById(Long id);

    List<LogResponseDto> getLogsForIncident(Long id);

    IncidentDetailsDto getIncidentDetails(Long id);
}