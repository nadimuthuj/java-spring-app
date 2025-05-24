
# Employee Directory Spring Boot App

## Features
- Simple REST API for employee data
- In-memory storage (no DB)

## Running Locally
```bash
./mvnw spring-boot:run
```

## Building Jar
```bash
./mvnw package
```

## Running with Docker
```bash
docker build -t employee-directory .
docker run -p 8080:8080 employee-directory
```

## Kubernetes Deployment
```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

## Access
- List employees: `GET /employees`
- Add employee: `POST /employees`
- Get employee: `GET /employees/{id}`

Example:
```bash
curl -X POST -H "Content-Type: application/json" \
    -d '{"id":1,"name":"Alice","role":"Engineer"}' \
    http://localhost:8080/employees
```
