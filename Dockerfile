FROM maven:3.8.4-openjdk-17 as builder


COPY ./b-config /app/src/main/resources


WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.war /app/*.war
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.war"]
