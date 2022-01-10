FROM openjdk
EXPOSE 8080
# ADD (first part is SOURCE, second part is DESTINATION in the Docker image)
ADD target/patient-0.0.1-SNAPSHOT.jar /patient-0.0.1-SNAPSHOT.jar
COPY wait-for-it.sh /wait-for-it.sh
#ENTRYPOINT ["java", "-jar", "/patient-0.0.1-SNAPSHOT.jar"] - we do not need this very entry point 
# here because it is overridden in docker-compose.yml