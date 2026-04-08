package com.krishna.Incident_Query_Service.DTO;
public class LogResponseDto {

    private String id;
    private String serviceName;
    private String level;
    private String message;
    private long timestamp;
    
    
	public LogResponseDto(String id, String serviceName, String level, String message, long timestamp) {
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.level = level;
		this.message = message;
		this.timestamp = timestamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

    // constructor + getters
    
}
