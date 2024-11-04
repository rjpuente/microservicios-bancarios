# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado al contenedor
ARG JAR_FILE=build/libs/tech_test-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Exponer el puerto de la aplicación
EXPOSE 3000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
