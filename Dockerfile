FROM openjdk:17-slim
WORKDIR /app
COPY target/payment-action-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
