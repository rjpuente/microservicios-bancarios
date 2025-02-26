# Stage 1: Compilación y generación del JAR
FROM gradle:8.2.1-jdk17 AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
# Ejecuta la tarea bootJar para generar el JAR ejecutable
RUN ./gradlew bootJar --no-daemon

# Stage 2: Imagen final para ejecutar la aplicación
FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=build/libs/cuenta-service-0.0.1.jar
# Copia el JAR generado en el stage anterior
COPY --from=builder /home/gradle/project/${JAR_FILE} app.jar
# Expone el puerto que usará la aplicación (internamente 3000)
EXPOSE 3000
# Define el ENTRYPOINT para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
