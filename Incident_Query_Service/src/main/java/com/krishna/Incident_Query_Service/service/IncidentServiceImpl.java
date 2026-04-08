package com.krishna.Incident_Query_Service.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.krishna.Incident_Query_Service.DTO.IncidentDetailsDto;
import com.krishna.Incident_Query_Service.DTO.IncidentResponseDto;
import com.krishna.Incident_Query_Service.DTO.LogResponseDto;
import com.krishna.Incident_Query_Service.exception.ResourceNotFoundException;
import com.krishna.Incident_Query_Service.model.Incident;
import com.krishna.Incident_Query_Service.model.LogEvent;
import com.krishna.Incident_Query_Service.repository.IncidentRepository;
import com.krishna.Incident_Query_Service.repository.LogRepository;

@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;
    private final LogRepository logRepository;

    private static final long TIME_WINDOW_MINUTES = 5;

    public IncidentServiceImpl(IncidentRepository incidentRepository,
                               LogRepository logRepository) {
        this.incidentRepository = incidentRepository;
        this.logRepository = logRepository;
    }

    @Override
    public Page<IncidentResponseDto> getAllIncidents(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("startTime").descending()
        );

        return incidentRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    @Override
    public IncidentResponseDto getIncidentById(Long id) {
        return mapToDto(getIncidentEntity(id));
    }

    @Override
    public List<LogResponseDto> getLogsForIncident(Long id) {

        Incident incident = getIncidentEntity(id);

        // ✅ Convert epoch → LocalDateTime
        LocalDateTime incidentTime = Instant.ofEpochMilli(incident.getStartTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        // ✅ Define time window
        LocalDateTime start = incidentTime.minusMinutes(TIME_WINDOW_MINUTES);
        LocalDateTime end = incidentTime.plusMinutes(TIME_WINDOW_MINUTES);

        // 🔥 Convert to epoch millis (IMPORTANT FIX)
        long startMillis = start.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();

        long endMillis = end.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();

        // 🔥 Query Elasticsearch using long range
        List<LogEvent> logs = logRepository.findLogsByServiceAndTimeRange(
                incident.getServiceName(),
                startMillis,
                endMillis
        );

        return logs.stream()
                .sorted(Comparator.comparing(LogEvent::getTimestamp))
                .map(this::mapLogDto)
                .toList();
    }

    @Override
    public IncidentDetailsDto getIncidentDetails(Long id) {

        IncidentResponseDto incident = getIncidentById(id);
        List<LogResponseDto> logs = getLogsForIncident(id);

        return new IncidentDetailsDto(incident, logs);
    }

    private Incident getIncidentEntity(Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Incident not found with id: " + id)
                );
    }

    private IncidentResponseDto mapToDto(Incident i) {
        return new IncidentResponseDto(
                i.getId(),
                i.getServiceName(),
                i.getIncidentType(),
                i.getSeverity(),
                i.getStartTime(),
                i.getStatus(),
                i.getEndTime()
        );
    }

    private LogResponseDto mapLogDto(LogEvent l) {
        return new LogResponseDto(
                l.getId(),
                l.getServiceName(),
                l.getLevel(),
                l.getMessage(),
                l.getTimestamp()
        );
    }
}