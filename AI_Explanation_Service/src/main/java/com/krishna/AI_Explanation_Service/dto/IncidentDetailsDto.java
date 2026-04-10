package com.krishna.AI_Explanation_Service.dto;

import java.util.List;

public class IncidentDetailsDto {
    private IncidentResponseDto incident;
    private List<LogResponseDto> logs;
	public IncidentResponseDto getIncident() {
		return incident;
	}
	public void setIncident(IncidentResponseDto incident) {
		this.incident = incident;
	}
	public List<LogResponseDto> getLogs() {
		return logs;
	}
	public void setLogs(List<LogResponseDto> logs) {
		this.logs = logs;
	}
    
    
}