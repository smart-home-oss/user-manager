# user-manager

## Setup

### Local files location

By default the app and dockerized containers will save files under: 

`/home/$USER/.smart-home`

> `$USER` is the currently logged user.

### Run / Build

#### Java app + Docker infra
Run Keycloak and MySQL. 

> Our Keycloak is using MySQL as database, but we can use it as well for our projects.
```bash
docker-compose up -d;
```

Run app using H2
```bash
# 1. run h2 server first
h2-server-run.sh;

# 2. then compile and run the app, H2 is used by default
mvn clean package spring-boot:run;
```

Run app using MySQL
```bash
# 1. we need to create the schema for our app first
mysql-setup.sh;

# 2. then compile and run the app, select mysql profile
mvn clean package spring-boot:run -Dspring.profiles.active=mysql;
```