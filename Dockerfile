FROM eclipse-temurin:22-jre

WORKDIR /app

COPY build/libs/OnlineLearnSystem-1.0-SNAPSHOT-all.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]