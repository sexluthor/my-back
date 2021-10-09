FROM openjdk:11
ADD target/back.jar back.jar

ENV PG_USERNAME=postgres\
    PG_PASSWORD=root\
    PG_HOST=localhost:5434\
    PG_DATABASE=vtb\
    KEYCLOAK_HOST=localhost:8080\
    KEYCLOAK_REALM=master

EXPOSE 80
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "back.jar"]
