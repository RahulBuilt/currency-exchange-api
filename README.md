# Currency Exchange API

A production-style backend service built with Java 21 and Spring Boot 3 for real-time currency exchange rate lookup and conversion workflows.

The service exposes REST APIs structured around a layered architecture with validation, persistence, caching, containerization, OpenAPI documentation, and CI — designed to reflect real-world backend engineering practices.

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- PostgreSQL
- Redis
- Docker / Docker Compose
- Swagger / OpenAPI
- JUnit 5
- GitHub Actions

## Features

- Retrieve latest exchange rates between currency pairs
- Convert currency amounts with persisted transaction records
- Redis caching for low-latency rate lookups
- Request payload validation with consistent error responses (Problem Details)
- Health and metrics endpoints via Spring Actuator
- Fully containerized with Docker Compose

## Running Locally

Requires Docker Desktop.

```bash
docker-compose up --build
```

| Endpoint | URL |
|---|---|
| API Base | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Health Check | http://localhost:8080/actuator/health |

## API Examples

### Get exchange rate

```http
GET /api/v1/rates/EUR/USD
```

### Convert currency

```http
POST /api/v1/conversions
Content-Type: application/json

{
  "sourceCurrency": "EUR",
  "targetCurrency": "USD",
  "amount": 100
}
```

## Supported Currency Pairs

EUR, USD, GBP, INR and more. Seed data included for local testing.

## Architecture
Controller
↓
Service
↓
Repository
↓
PostgreSQL / Redis

## Improvements Made

- Fixed missing `-parameters` compiler flag causing `@PathVariable` reflection failures in Java 21
- Added `Serializable` interface to DTO classes for Redis cache compatibility
- Extended seed data to include INR and GBP pairs

## Roadmap

- Integrate live exchange-rate provider (frankfurter.app)
- Add scheduled daily rate synchronization
- Add JWT authentication
- Add rate limiting
- Add Kubernetes manifests

## Author

Rahul Kumar Yadav  
B.E. (Hons.) Computer Science, BITS Pilani Goa Campus  
GitHub: github.com/RahulBuilt