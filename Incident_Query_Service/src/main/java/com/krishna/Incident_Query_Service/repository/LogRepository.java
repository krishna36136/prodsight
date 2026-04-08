package com.krishna.Incident_Query_Service.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.krishna.Incident_Query_Service.model.LogEvent;

public interface LogRepository extends ElasticsearchRepository<LogEvent, String> {

	@Query("""
			{
			  "bool": {
			    "must": [
			      { "match": { "serviceName": "?0" }},
			      {
			        "range": {
			          "timestamp": {
			            "gte": ?1,
			            "lte": ?2
			          }
			        }
			      }
			    ]
			  }
			}
			""")
			List<LogEvent> findLogsByServiceAndTimeRange(
			        String serviceName,
			        long start,
			        long end
			);
}