# 🚀 AI-Powered Production Incident Monitoring System

A microservices-based production monitoring platform that simulates real-world application observability. The system continuously generates application logs and service metrics, detects anomalies in real time, correlates incidents with relevant logs, and uses AI to generate human-readable incident explanations and recommendations.

The project demonstrates how modern distributed systems use **Kafka, Elasticsearch, MySQL, Spring Boot, Docker, React, and OpenAI** to build an intelligent monitoring platform.

---

# ✨ Features

* Real-time log ingestion using Kafka
* Real-time metrics streaming
* Automatic anomaly detection
* Incident generation and lifecycle management
* Fast log search using Elasticsearch
* Correlation of incidents with surrounding logs
* AI-generated incident summaries, probable causes, and recommendations
* Interactive React dashboard with pagination
* Containerized infrastructure using Docker

---

# 🏗️ System Architecture

## 1. Log Producer

* Spring Boot microservice acting as a Kafka Producer.
* Generates simulated application logs every **2 seconds**.
* Publishes logs to the **`log-events`** Kafka topic.

Each log contains:

* Service Name
* Log Level
* Message
* Timestamp

---

## 2. Log Ingestion Service

* Spring Boot microservice acting as a Kafka Consumer.
* Consumes logs from the **`log-events`** topic.
* Stores logs in **Elasticsearch** for efficient text search and time-based querying.

Elasticsearch is used because logs require fast full-text search and time-window analysis, which are not well suited for relational databases.

---

## 3. Metric Producer

* Spring Boot Kafka Producer.
* Generates service metrics every **5 seconds**.
* Publishes metrics to the **`metric-events`** Kafka topic.

Each metric contains:

* Service Name
* Timestamp
* Error Rate
* Average Latency
* Request Count

---

## 4. Incident Service

* Spring Boot Kafka Consumer.
* Consumes metrics from the **`metric-events`** topic.
* Applies rule-based anomaly detection using configurable thresholds.
* Automatically generates incidents when abnormal behavior is detected.

Each incident contains:

* Service Name
* Incident Type
* Severity
* Start Time
* End Time
* Status

Incidents are persisted in **MySQL** using **Spring Data JPA (Hibernate)**.

---

## 5. Incident Query Service

Provides REST APIs for querying incident information.

Supported operations include:

* Retrieve all incidents
* Retrieve incident by ID
* Retrieve correlated logs
* Retrieve detailed incident information

### Log Correlation

Instead of storing logs directly with incidents, the service dynamically correlates them by:

* Matching the same service name
* Searching logs occurring within a **±5 minute time window** around the incident

This approach closely resembles how modern observability platforms correlate logs with production incidents.

---

## 6. AI Explanation Service

Generates AI-powered explanations for incidents.

Workflow:

1. Receives an Incident ID.
2. Fetches incident details from the Incident Query Service using **Spring WebClient**.
3. Sends the incident context to the OpenAI API.
4. Returns structured AI insights.

Each AI response contains:

* Incident Summary
* Probable Cause
* Recommended Resolution

---

## 7. React Dashboard

Provides an interactive interface for monitoring incidents.

Features include:

* Paginated incident listing (10 incidents per page)
* View correlated logs
* Generate AI explanations
* Real-time interaction with backend REST APIs

---

# 🔄 End-to-End Workflow

```text
Log Producer
      │
      ▼
Kafka (log-events)
      │
      ▼
Log Ingestion Service
      │
      ▼
Elasticsearch


Metric Producer
      │
      ▼
Kafka (metric-events)
      │
      ▼
Incident Service
      │
      ▼
MySQL


React Dashboard
      │
      ▼
Incident Query Service
      │
      ├────────► MySQL (Incidents)
      │
      └────────► Elasticsearch (Related Logs)


AI Explanation Service
      │
      ├────────► Incident Query Service
      │
      └────────► OpenAI API
```

---

# 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Web
* Spring Data JPA (Hibernate)
* Spring WebClient

### Messaging

* Apache Kafka

### Databases

* MySQL
* Elasticsearch

### Frontend

* React

### AI Integration

* OpenAI API

### DevOps

* Docker

---

# 💡 Design Highlights

* Microservices architecture with clear separation of responsibilities.
* Event-driven communication using Apache Kafka.
* Efficient log indexing and retrieval using Elasticsearch.
* Rule-based anomaly detection for automated incident creation.
* Dynamic correlation of incidents with relevant logs using service name and time-window analysis.
* AI-assisted incident analysis using the OpenAI API.
* Containerized infrastructure with Docker for consistent local deployment.
