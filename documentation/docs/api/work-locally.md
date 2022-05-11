---
sidebar_position: 4
---

# Work with API locally

## Start

To run API locally you need installed [Docker Desktop](https://www.docker.com/products/docker-desktop/).

1. From the Git repo root go to Backend directory - `cd backend`
2. Build services - `docker-compose up audra-build`.

:::info
You need to rebuild services on every BE change or git checkout.
:::

1. Run services in _detached_ mode (BE, API Gateway, DB) - `docker-compose up -d audra-service`
2. API Documentation - [http://localhost:8080/graphiql](http://localhost:8080/graphiql)
3. Connect to API gateway with URI `http://localhost:8080/graphql`
4. To shut down all use `docker-compose down`

## Rebuild

1. Go to Docker Desktop GUI
2. Go to Containers
3. Stop `backend` -> `audra_service` container
4. Delete `backend` -> `audra_service` container
5. Go to Images
6. Delete `backend_audra_service` image
7. Go to Step #2 from Start instruction.
