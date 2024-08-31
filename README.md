
# Blackjack API

## Descripción
Este proyecto es una API para un juego de Blackjack desarrollado utilizando Spring Boot. La API permite gestionar jugadores, partidas y operaciones típicas dentro del juego como apostar, pedir carta y plantarse.

## Tecnologías Utilizadas
- **Spring Boot**: Versión 3.3.2 para la creación de la API REST.
- **MongoDB**: Base de datos NoSQL utilizada para almacenar información de los jugadores.
- **MySQL**: Base de datos SQL utilizada para gestionar los datos de las partidas.
- **Docker**: Utilizado para contenerizar la aplicación y facilitar su despliegue y distribución.
- **Maven**: Herramienta de gestión y construcción de proyectos.
- **Swagger/OpenAPI**: Usado para la documentación de la API y pruebas de los endpoints.

## Características
- **Gestión de Jugadores**: Crear, modificar y eliminar jugadores.
- **Gestión de Partidas**: Iniciar partidas, jugar turnos y finalizar partidas.
- **Autenticación**: Gestión de accesos y sesiones de usuarios.
- **Documentación API**: Swagger UI integrado para fácil acceso y prueba de endpoints.

## Requisitos
Para ejecutar este proyecto, necesitarás tener instalado:
- Java 17
- Maven
- Docker
- Docker Compose

## Configuración del proyecto
### Variables de Entorno
Configura las siguientes variables de entorno antes de ejecutar la aplicación:

```env
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:8889/blackjack
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
SPRING_DATA_MONGODB_HOST=localhost
SPRING_DATA_MONGODB_PORT=27017
SPRING_DATA_MONGODB_DATABASE=blackjack
```

## Instalación y Ejecución
### Con Docker Compose
Para construir y ejecutar la aplicación utilizando Docker Compose, sigue estos pasos:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/ArlenyAres/Game_BlackJack_Api
   cd Game_BlackJack_Api/blackjack
   ```

2. Construye y levanta los servicios con Docker Compose:
   ```bash
   docker-compose up --build
   ```

### Ejecución local sin Docker
Para ejecutar la aplicación localmente sin utilizar Docker, realiza los siguientes pasos:

1. Construye el proyecto con Maven:
   ```bash
   mvn clean package
   ```

2. Ejecuta la aplicación:
   ```bash
   java -jar target/blackjack-0.0.1-SNAPSHOT.jar
   ```

## Uso de la API
Accede a la documentación de la API y prueba los endpoints utilizando Swagger UI en:

```
http://localhost:8080/swagger-ui.html
```

## Contribuciones
Las contribuciones son bienvenidas! Por favor, realiza un fork del repositorio y envía un pull request con tus mejoras. Asegúrate de actualizar los tests según corresponda.

## Manejo de Errores
La API utiliza códigos de estado HTTP para indicar el resultado de las operaciones. A continuación, algunos ejemplos comunes:

- `200 OK`: La solicitud fue exitosa.
- `400 Bad Request`: La solicitud no fue procesada debido a un error en los parámetros enviados.
- `404 Not Found`: El recurso solicitado no fue encontrado.
- `500 Internal Server Error`: Error interno del servidor.



