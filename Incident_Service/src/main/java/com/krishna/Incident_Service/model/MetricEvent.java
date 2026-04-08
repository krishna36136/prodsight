package com.krishna.Incident_Service.model;

public class MetricEvent {

    private String serviceName;
    private long timestamp;
    private double errorRate;
    private double avgLatency;
    private int requestCount;
    
	public MetricEvent() {
		super();
	}
	
	public MetricEvent(String serviceName, long timestamp, double errorRate, double avgLatency, int requestCount) {
		super();
		this.serviceName = serviceName;
		this.timestamp = timestamp;
		this.errorRate = errorRate;
		this.avgLatency = avgLatency;
		this.requestCount = requestCount;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public double getErrorRate() {
		return errorRate;
	}
	public void setErrorRate(double errorRate) {
		this.errorRate = errorRate;
	}
	public double getAvgLatency() {
		return avgLatency;
	}
	public void setAvgLatency(double avgLatency) {
		this.avgLatency = avgLatency;
	}
	public int getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
    
    
}
