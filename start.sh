#!/bin/bash

echo ">>>>>> Avvio container PostgreSQL..."
docker start postgres-userapi

echo ">>>>>> Inizializza app..."
sleep 5
cd ~/workspace/user-api

echo ">>>>>> Avvio server user-api..."
mvn clean install
mvn spring-boot:run -Dspring-boot.run.arguments=--server.address=0.0.0.0
