# Architecture Notes

This project follows a simple layered architecture.

## API Design Decisions

- Versioned API path: `/api/v1`
- DTOs are separate from JPA entities
- Validation happens at the API boundary
- Errors use Spring ProblemDetail for consistent responses
- Conversion history is stored separately from exchange-rate data

## Production Improvements

The current project uses demo rates to keep the sample easy to run locally. A production version would add:

- external exchange-rate API integration
- scheduled sync jobs
- retries and circuit breakers
- stronger monitoring
- authentication and authorization
- integration tests with Testcontainers
