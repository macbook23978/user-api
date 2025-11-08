# User API

Simple REST API for managing users.

## Requirements
- Java 17
- Maven
- Docker (for PostgreSQL)

## Start Database
```
docker start postgres-userapi
```

## Run Application
```
mvn spring-boot:run
```

## Test Endpoints
```
curl http://localhost:8080/users
curl http://localhost:8080/users/1
```

## Project Structure
```
src/main/java/com/example/
  controller/
  model/
  repository/
  service/
```

