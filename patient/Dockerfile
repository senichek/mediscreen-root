FROM openjdk
EXPOSE 8080
# ADD (first part is SOURCE, second part is DESTINATION in the Docker image)
ADD target/patient-0.0.1-SNAPSHOT.jar /patient-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/patient-0.0.1-SNAPSHOT.jar"]
# CMD run command is: docker run -p 9090 patient-rest-api