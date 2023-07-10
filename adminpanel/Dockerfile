FROM openjdk:17-oracle
COPY target/adminpanel-0.0.1-SNAPSHOT.jar backend_app.jar
EXPOSE 8081
ENTRYPOINT [ "java","-jar","backend_app.jar" ]