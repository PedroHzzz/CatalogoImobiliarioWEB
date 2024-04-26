FROM maven:3.8.4-openjdk-17 as builder

COPY . /app

WORKDIR /app

RUN mvn clean package -Dmaven.test.skip=true


FROM openjdk:17-oracle


COPY --from=builder /app/target/*.jar /app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]