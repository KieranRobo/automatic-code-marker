FROM openjdk:14-jdk-alpine

RUN apk add python
RUN python -c "import sys; print(sys.executable)"

COPY ./target/final-year-project-1.0-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "-Dspring.profiles.active=default", "-Dpython.import.site=false", "/app.jar"]
