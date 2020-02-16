#!/usr/bin/env bash

KEYCLOACK_CONTAINER_NAME=keycloak-um-shoss
REAL_NAME=Master

docker exec -it ${KEYCLOACK_CONTAINER_NAME} /opt/jboss/keycloak/bin/standalone.sh \
-Djboss.socket.binding.port-offset=100 -Dkeycloak.migration.action=export \
-Dkeycloak.migration.provider=singleFile \
-Dkeycloak.migration.realmName=${REAL_NAME} \
-Dkeycloak.migration.usersExportStrategy=REALM_FILE \
-Dkeycloak.migration.file=/tmp/${REAL_NAME}.json