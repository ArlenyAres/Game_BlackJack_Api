# Usar una imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# el archivo pom.xml y los archivos de código fuente al contenedor
COPY pom.xml .
COPY src ./src
COPY target/blackjack-0.0.1-SNAPSHOT.jar /app/blackjack.jar

# Compilar el proyecto usando Maven
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "blackjack.jar"]