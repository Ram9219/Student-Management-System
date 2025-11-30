# Builder: build the fat jar
FROM maven:3.8.8-openjdk-11-slim AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

# Runtime: smaller JRE image
FROM eclipse-temurin:11-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENV JAVA_OPTS=""
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
