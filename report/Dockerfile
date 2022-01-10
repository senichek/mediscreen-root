FROM openjdk
EXPOSE 8083
# ADD (first part is SOURCE, second part is DESTINATION in the Docker image)
ADD target/report-0.0.1-SNAPSHOT.jar /report-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/report-0.0.1-SNAPSHOT.jar"]
# CMD run command is: docker run -p 9093 report-rest-api