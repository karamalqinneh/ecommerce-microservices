FROM openjdk:17-slim
LABEL authors="kqinneh"

ARG JAR_FILE
ENV KAFKA_URL=broker:29092
ENV EUREKA_URL=http://service-registry:8761/eureka

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

#execute the application
ENTRYPOINT ["java","-jar","/app.jar"]