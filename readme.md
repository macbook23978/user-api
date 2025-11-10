# MULE4 user-api


## Server

- **Sistema operativo:** Xubuntu 25
- **Host:** 192.168.1.10 (Wi-Fi locale)
- **Java:** JDK 21 - OpenJDK (build 21.0.9-ea+8-Ubuntu-1)
- **Maven:** Apache Maven 3.9.9
- **Applicazione:** user-api (Spring Boot 3.3.3)
- **Database:** PostgreSQL (Docker container)
- **DB Name:** userdb
- **DB User:** user / Password: password
- **Porta applicazione:** 8080
- **Accesso API:** da qualsiasi dispositivo in rete locale


## Database – Schema principale

| Campo     | Tipo        | Note                     |
|-----------|------------|-------------------------|
| id        | SERIAL     | PK, autoincrement       |
| firstName | VARCHAR    | Nome utente             |
| lastName  | VARCHAR    | Cognome utente          |
| email     | VARCHAR    | Email                   |
| address   | VARCHAR    | Indirizzo               |


## Endpoints

Base URL: `http://192.168.1.10:8080/users`

| Metodo | Endpoint                          | Descrizione                                    | Input / Output JSON |
|--------|----------------------------------|-----------------------------------------------|-------------------|
| GET    | `/users`                          | Legge tutti gli utenti                         | Lista di utenti   |
| GET    | `/users/{id}`                     | Legge singolo utente per ID                   | Utente singolo    |
| POST   | `/users`                          | Crea nuovo utente                              | `{ "firstName": "...", "lastName": "...", "email": "...", "address": "..." }` |
| PUT    | `/users/{id}`                     | Aggiorna intera entità utente                 | `{ "firstName": "...", "lastName": "...", "email": "...", "address": "..." }` |
| PATCH  | `/users/{id}`                     | Aggiorna parzialmente campi dell’utente       | `{ "campo": "valore" }` |
| DELETE | `/users/{id}`                     | Cancella utente per ID                         | {id}                 |
| GET    | `/users/search?firstName=&lastName=` | Ricerca utenti per nome e/o cognome            | Lista di utenti filtrati |


### Note

- Tutti gli endpoint restituiscono JSON.
- Ricerca accetta uno o entrambi i parametri firstName e lastName.
- PATCH accetta i campi firstName, lastName, email, address.
- Tutti i dati persistono nel database PostgreSQL-Docker.
- Endpoint disponibili in rete locale tramite curl, via browser o app (es. Postman).

