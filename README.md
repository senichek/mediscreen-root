# Mediscreen Application

## Follow these steps to run this app in Docker:
1. Download, install and run **Docker**: https://docs.docker.com/get-docker/
2. Clone this project
3. Build the Jars (.jar) of each of 4 subprojects: \
   a.&nbsp;&nbsp;&nbsp;open the Terminal (for Windows press "Windows + R", type CMD and press Enter) \
   b.&nbsp;&nbsp;&nbsp;navigate to a subproject folder \
   c.&nbsp;&nbsp;&nbsp;type "mvn package" and press Enter \
    For example:\
   *C:/mediscreen-root/note/mvn package*

4. Navigate to the project's folder and run the file called "docker-compose.yml" by typing the following command and pressing Enter:\
   **docker-compose up**\
   For example:\
   *C:/mediscreen-root/docker-compose up*


## URL of the main page:
http://localhost:8082/patient/list