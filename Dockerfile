FROM openjdk:17
ADD target/glb.jar glb.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/glb.jar"]