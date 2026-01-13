# Streaker – Practice Tracking API

Streaker is a Spring Boot–based RESTful API designed to help users track skill practice sessions, maintain consistency, and build long-term learning streaks.  
The project follows a clean, layered architecture and uses modern Java libraries for maintainability and scalability.

---

## 🚀 Features

- Log daily practice sessions for skills
- Validate that practice is logged only for active skills
- Prevent duplicate practice entries for the same skill on the same day
- Clean DTO-based API design
- Automated Entity ↔ DTO mapping using MapStruct

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MapStruct
- Lombok
- H2 / PostgreSQL
- Maven

---

## 🏗️ Architecture Overview

The application follows a standard layered architecture:

- **Controller** – Handles HTTP requests and responses
- **Service** – Contains business logic and validations
- **Mapper** – Converts Entities to DTOs and vice versa (MapStruct)
- **Repository** – Database access using Spring Data JPA
- **DTO** – Request and response models
- **Entity** – JPA entities representing database tables

---
## 📁 Project Structure
src/main/java/com/example/streaker
├── controller
├── service
├── mapper
├── repository
├── dto
└── entity
--
# API Endpoints
1. Log a Practice Session

POST /api/sessions

Request Body
{
"skillId": 1,
"practiceDate": "2024-05-20",
"durationMinutes": 60,
"effortLevel": 8,
"notes": "Focused on advanced concepts."
}

Rules

Skill must exist and be active

Only one session per skill per day is allowed

2. Get All Practice Sessions

GET /api/sessions

Response

Returns all logged practice sessions

Data is returned in a flattened DTO format

---
# Request Flow

Client sends a JSON request

Controller validates the input

Service layer:

Verifies skill status

Checks for duplicate session entries

Mapper converts DTO to Entity

Repository saves the entity

Response DTO is returned to the client

---
# Design Principles

Separation of concerns

DTO-first API design

No business logic in controllers

Compile-time mapping for better performance

Database-agnostic persistence layer