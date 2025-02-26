# Stage 1: Build con Gradle
FROM gradle:8.2.1-jdk17 AS builder
WORKDIR /home/gradle/project
# Copia los archivos del proyecto al contenedor
COPY --chown=gradle:gradle . .
# Construye el JAR usando el wrapper de Gradle
RUN ./gradlew bootJar --no-daemon

# Stage 2: Imagen final con OpenJDK 17
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copia el JAR desde el stage builder. Ajusta el nombre del archivo JAR según tu build.
COPY --from=builder /home/gradle/project/build/libs/cliente-service-0.0.1.jar app.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "app.jar"]
