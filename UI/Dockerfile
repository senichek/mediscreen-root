FROM openjdk
EXPOSE 8082
# ADD (first part is SOURCE, second part is DESTINATION in the Docker image)
ADD target/UI-0.0.1-SNAPSHOT.jar /UI-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/UI-0.0.1-SNAPSHOT.jar"]
# CMD run command is: docker run -p 9093 UI