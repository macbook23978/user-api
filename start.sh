#!/bin/bash

echo "==> Avvio container PostgreSQL..."
docker start postgres-userapi

echo "==> Attendo 3 secondi perché il DB si inizializzi..."
sleep 3

echo "==> Avvio server Spring Boot in ascolto su tutte le interfacce..."
cd ~/workspace/user-api
mvn clean compile
mvn spring-boot:run -Dspring-boot.run.arguments=--server.address=0.0.0.0
