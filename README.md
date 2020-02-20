# user-manager

[![Build Status](https://travis-ci.com/smart-home-oss/house-manager.svg?branch=master)](https://travis-ci.com/smart-home-oss/house-manager)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=smart-home-oss_house-manager&metric=alert_status)](https://sonarcloud.io/dashboard?id=smart-home-oss_house-manager)

## System requirements

1. Java Development Kit 8+
1. Maven, 
    - _optionally you can use `mvnw`_
1. Docker
1. `docker-compose`
1. Postman

## Docs

The project docs are located under: `/docs`. 
They are split if dedicated files per topic.

## Infrastructure

The app requires couple of external dependencies to run: 
database and authentication server.

The files that are launching that are located under: `/infra`