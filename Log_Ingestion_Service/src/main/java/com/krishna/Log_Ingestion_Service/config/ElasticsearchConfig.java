package com.krishna.Log_Ingestion_Service.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;

import com.krishna.Log_Ingestion_Service.model.LogEvent;

@Configuration
public class ElasticsearchConfig {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @PostConstruct
    public void createIndex() {
        try {
            IndexOperations indexOps = elasticsearchOperations.indexOps(LogEvent.class);
            indexOps.create();
            indexOps.putMapping(indexOps.createMapping());
            System.out.println("✅ Index created");
        } catch (Exception e) {
            System.out.println("⚠️ Index already exists or error: " + e.getMessage());
        }
    }
}