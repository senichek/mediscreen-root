FROM openjdk
EXPOSE 8081
# ADD (first part is SOURCE, second part is DESTINATION in the Docker image)
ADD target/note-0.0.1-SNAPSHOT.jar /note-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/note-0.0.1-SNAPSHOT.jar"]
# CMD run command is: docker run -p 9091 note-rest-api