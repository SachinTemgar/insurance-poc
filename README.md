# Insurance POC

Two Java applications sharing one H2 database, built to compare a classic Java EE
stack against a modern Spring Boot microservice.

| Module | Stack | Port |
|---|---|---|
| `product-web` | JSP, Servlet, EJB 3.2, Open Liberty | 9080 |
| `policy-api` | Spring Boot 2.7, Spring Data JPA, Swagger | 8080 |

Both run on Java 8.

## Prerequisites

- JDK 8 — Azul Zulu is recommended on Apple Silicon, since Temurin has no Java 8 ARM64 build
- Maven 3.6 or later

## Running

Start H2 first. Both applications connect to it over TCP, because two JVMs cannot
open the same embedded H2 file — the second one to start would fail.

### 1. Start H2

```bash
mkdir -p data
java -cp tools/h2-2.2.224.jar org.h2.tools.Server \
     -tcp -tcpAllowOthers -ifNotExists -web
```

Leave this running. The web console is at http://localhost:8082.

### 2. Create the schema (first run only)

Connect the H2 console to:

```
jdbc:h2:tcp://localhost:9092/./data/policydb
```

User `sa`, no password. Run `db/schema.sql`, then `db/data.sql`.

### 3. Start App 1

```bash
cd product-web
mvn liberty:dev
```

Open http://localhost:9080/product-web/ and click **Load Products**.

### 4. Start App 2

```bash
cd policy-api
mvn spring-boot:run
```

## API

| Method | Path | Description |
|---|---|---|
| GET | `/api/policies` | All sold policies, with product names |
| GET | `/api/policies/{customerId}` | Policies for one customer; 404 if none |

Swagger UI: http://localhost:8080/swagger-ui.html

## Architecture

App 1 follows the classic Java EE request flow: a servlet receives the request,
calls a stateless session bean for the business logic, and forwards to a JSP for
rendering. Open Liberty supplies the EJB container, the servlet container, and the
JDBC connection pool, configured in
`product-web/src/main/liberty/config/server.xml`.

App 2 is a standalone microservice. A REST controller calls a service, which uses
Spring Data JPA repositories. The web server is embedded in the jar, so the service
runs with `java -jar` and needs no external server.

## Database

Two tables, seeded with 4 products and 5 policies.

- `product` — product_id, name, description, premium
- `customer_policy` — id, customer_name, customer_id, product_id

The database file under `data/` is not tracked in git. Recreate it from the scripts
in `db/`.

## Notes

`policy-api` sets `spring.jpa.hibernate.ddl-auto=none` so Hibernate does not alter
the schema owned by the SQL scripts.

Sharing one database between two services is a deliberate simplification for this
POC. Production microservices would each own their data.