package com.krishna.Incident_Service.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krishna.Incident_Service.model.Incident;
import com.krishna.Incident_Service.repository.IncidentRepository;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
		super();
		this.incidentRepository = incidentRepository;
	}



	public void createIncident(String serviceName, String type, String severity) {

        Optional<Incident> existingIncident =
                incidentRepository.findByServiceNameAndIncidentTypeAndStatus(
                        serviceName,
                        type,
                        "OPEN"
                );

        if (existingIncident.isPresent()) {
            System.out.println("Incident already exists. Skipping duplicate.");
            return;
        }

        Incident incident = new Incident();

        incident.setServiceName(serviceName);
        incident.setIncidentType(type);
        incident.setSeverity(severity);
        incident.setStartTime(System.currentTimeMillis());
        incident.setStatus("OPEN");

        incidentRepository.save(incident);

        System.out.println("Incident created for service: " + serviceName);
    }
	
	public void resolveIncident(String serviceName, String type) {

	    Optional<Incident> incident =
	            incidentRepository.findByServiceNameAndIncidentTypeAndStatus(
	                    serviceName,
	                    type,
	                    "OPEN"
	            );

	    if (incident.isEmpty()) {
	        return;
	    }

	    Incident existingIncident = incident.get();

	    existingIncident.setStatus("RESOLVED");
	    existingIncident.setEndTime(System.currentTimeMillis());

	    incidentRepository.save(existingIncident);

	    System.out.println("Incident resolved for service: " + serviceName);
	}
}