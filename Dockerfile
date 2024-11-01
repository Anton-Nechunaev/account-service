FROM openjdk:17-jdk-alpine

WORKDIR /app

EXPOSE 8084

COPY account-impl/target/*.jar account-impl.jar

ENTRYPOINT ["java", "-jar", "account-impl.jar"]