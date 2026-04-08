package com.krishna.Log_Producer.model;

public class LogEvent {

    private String serviceName;
    private String level;
    private String message;
    private long timestamp;

    public LogEvent() {}

    public LogEvent(String service, String level, String message, long timestamp) {
        this.serviceName = service;
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getserviceName() {
        return serviceName;
    }

    public void setserviceName(String serviceName) {
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
}