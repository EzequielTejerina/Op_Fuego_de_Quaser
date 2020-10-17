# Op_Fuego_de_Quaser

## Desafío
Como jefe de comunicaciones rebelde, tu misión es crear un programa en Java que retorne
la fuente y contenido del mensaje de auxilio. Para esto, cuentas con tres satélites que te
permitirán triangular la posición, ¡pero cuidado! el mensaje puede no llegar completo a cada
satélite debido al campo de asteroides frente a la nave.
```
Posición de los satélites actualmente en servicio
● Kenobi: [-500, -200]
● Skywalker: [100, -100]
● Sato: [500, 100]

```
## Tecnologías

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MongoDB](https://www.mongodb.com/es)
- [Maven](https://maven.apache.org/)
- [Google Cloud](https://cloud.google.com/)
- [JUnit 4](https://junit.org/junit4/)
- [Swagger](https://swagger.io/)

## Instalación

Es necesario tener instalado:

- [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org/)
- [MongoDB](https://www.mongodb.com/es)
- [Git](https://git-scm.com/)

```
$ git clone https://github.com/EzequielTejerina/Op_Fuego_de_Quaser.git
$ cd challenge
$ mvn clean package
$ mvn spring-boot:run
```
De esta manera, la aplicación se encontraría levantada en 'http://localhost:8080/challenge/' y habría que utilizar esta URL si quisiéramos probar localmente las APIs a través de [Postman](https://www.getpostman.com/) por ejemplo.

## Uso
Para ver los Ingresando a la URL http://localhost:8080/swagger-ui.html