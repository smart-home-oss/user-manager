# user-manager

## Setup

### Local files location

By default the app will save it's files under: 

`/home/$USER/.smart-home`

While `$USER` is the currently logged user.

### Run / Build

There are 2 main ways to run the app.

#### Java app + Docker infra
```bash
docker-compose up -d;
export.sh;
mvn clean package spring-boot:run;
```

#### Docker app and infra
```bash
docker-compose build;
export.sh;
docker-compose up -d;
```

## Links

1. [Understanding OAuth2 and Building a Basic Authorization Server of Your Own](https://medium.com/google-cloud/understanding-oauth2-and-building-a-basic-authorization-server-of-your-own-a-beginners-guide-cf7451a16f66)
1. [Run a single MySQL query from the command line](https://electrictoolbox.com/run-single-mysql-query-command-line/)
1. [MySQL :: Connection URL Syntax](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-jdbc-url-format.html)
1. [Keycloak Docker image](https://hub.docker.com/r/jboss/keycloak/)
