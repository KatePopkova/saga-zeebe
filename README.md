# Saga code example on Camunda Platform 8
This project contains:
1. infrastructure (Camunda Platform 8, ElasticSearch, MongoDB), 
2. API for making an order (order service),
3. worker apps (inventory service, payment service).

For running infra cluster run the command:
docker compose up -d

For stopping infra cluster run the command:
docker compose down

## Architecture and Patterns:
- https://www.digitalocean.com/blog/monolithic-vs-microservice-architecture
- https://microservices.io/patterns/
- https://microservices.io/patterns/data/saga.html
- https://microservices.io/patterns/data/database-per-service
- https://www.vinsguru.com/architectural-pattern-orchestration-saga-pattern-implementation-using-kafka/
- https://bool.dev/blog/detail/saga-pattern-i-raspredelennye-tranzaktsii

## Camunda Platform 8 Docs:
https://docs.camunda.io/docs/self-managed/about-self-managed/
