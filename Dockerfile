FROM adoptopenjdk/openjdk11
RUN addgroup  spring && adduser spring -G spring
USER spring:spring
ARG JAR_FILE=target/docker-algorithm-1.0.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]