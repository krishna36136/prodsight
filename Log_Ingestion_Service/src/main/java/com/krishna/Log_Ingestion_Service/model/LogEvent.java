package com.krishna.Log_Ingestion_Service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "logs", createIndex = false)
public class LogEvent {

    @Id
    private String id;

    private String serviceName;
    private String level;
    private String message;
    private String timestamp;
    
	public LogEvent() {
		super();
	}
	public LogEvent(String id, String serviceName, String level, String message, String timestamp) {
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
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
    
    
}