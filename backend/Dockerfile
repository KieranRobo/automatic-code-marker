FROM openjdk:14-jdk-alpine
COPY ./target/final-year-project-1.0-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "-Dspring.profiles.active=default", "/app.jar"]