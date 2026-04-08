package com.krishna.Incident_Query_Service.model;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;

@Document(indexName = "logs")
public class LogEvent {

    @Id
    private String id;

    private String serviceName;
    private String level;
    private String message;

    private long timestamp;

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
    
    
    // getters/setters
}