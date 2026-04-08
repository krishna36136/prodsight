package com.krishna.Incident_Service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishna.Incident_Service.model.Incident;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
	Optional<Incident> findByServiceNameAndIncidentTypeAndStatus(
            String serviceName,
            String incidentType,
            String status
    );
}
