FROM openjdk:17

COPY target/ms-yanki-1.0.0-SNAPSHOT.jar java-app.jar

RUN fc-cache -f -v

ENTRYPOINT ["java","-jar","java-app.jar"]