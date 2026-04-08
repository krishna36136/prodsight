package com.krishna.Log_Ingestion_Service.repository;
import com.krishna.Log_Ingestion_Service.model.LogEvent;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<LogEvent, String> {
}