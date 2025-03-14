FROM eclipse-temurin:11-jdk-alpine

WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
RUN cp target/*.jar app.jar

EXPOSE 8090

CMD ["java", "-jar", "app.jar"]
