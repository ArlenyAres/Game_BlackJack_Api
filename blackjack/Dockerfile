# Usar una imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR construido desde Maven en el host al contenedor
COPY target/blackjack-0.0.1-SNAPSHOT.jar /app/blackjack.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "blackjack.jar"]


