package com.krishna.AI_Explanation_Service.dto;


public class ExplanationResponseDto {
    private String summary;
    private String probableCause;
    private String recommendation;
    
    
	public ExplanationResponseDto(String summary, String probableCause, String recommendation) {
		super();
		this.summary = summary;
		this.probableCause = probableCause;
		this.recommendation = recommendation;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getProbableCause() {
		return probableCause;
	}
	public void setProbableCause(String probableCause) {
		this.probableCause = probableCause;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
    
    
}
