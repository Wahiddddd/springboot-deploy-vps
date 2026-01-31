
# Spring Boot Deployment Exercise

Panduan utama ada di [QUICKSTART.md](QUICKSTART.md).

## Stack

- Spring Boot + Actuator
- MySQL (container `mysql`)
- Redis (container `redis`) untuk caching `GET /products`
- Docker Compose untuk local & VPS

## Quick checks

- Health: `GET /actuator/health`
- Hello: `GET /hello?name=arif`
- Products: `GET /products`

Lihat detail cara run, setup `.env`, dan troubleshooting di [QUICKSTART.md](QUICKSTART.md) dan [VPS_SETUP.md](VPS_SETUP.md).

