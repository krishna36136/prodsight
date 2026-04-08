package com.krishna.Incident_Query_Service.DTO;

public class IncidentResponseDto {

    private Long id;
    private String serviceName;
    private String incidentType;
    private String severity;
    private long startTime;
    private String status;
    private Long endTime;
    
	public IncidentResponseDto(Long id, String serviceName, String incidentType, String severity, long startTime,
			String status, Long endTime) {
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.incidentType = incidentType;
		this.severity = severity;
		this.startTime = startTime;
		this.status = status;
		this.endTime = endTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIncidentType() {
		return incidentType;
	}
	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

    // constructor + getters
}