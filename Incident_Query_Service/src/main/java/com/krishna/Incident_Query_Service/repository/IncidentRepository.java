package com.krishna.Incident_Query_Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishna.Incident_Query_Service.model.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}