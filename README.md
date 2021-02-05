# API de usuarios 

_API REST de basado en Spring Boot_

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Para instalar el proyecto se debe clonar o descargar el repositorio.
Para clonar:
```
git clone git@github.com:mdwarg/melitest.git
```
Descargar ZIP:
```
https://github.com/mdwarg/melitest/archive/master.zip
```

Mira **Deployment** para conocer como desplegar el proyecto.


### Pre-requisitos 📋

_Para instalar la aplicación necesitas tener instalado:_

* [Java 8 JDK](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* [Gradle](https://gradle.org/install/)
* [Docker](https://docs.docker.com/install/)

## Correr la aplicación 🚀

_Correr la aplicación utilizando Gradle_

Se puede correr la apliación corriendo el siguiente comando en el directorio del proyecto:
```
gradle bootrun
```

## Ejecutando las pruebas ⚙️

_Ejecutar las pruebas usando gradle_

Dentro del directorio donde se encuentra el código del proyecto ejecutamos:
```
gradle test
```

## Deployment 📦

_Para desplegar el proyecto debemos hacer el empaquetado de la apicacion utilizando gradle_

Se debe establecer la variable de entorno con el puerto que utilizara la aplicacion con el siguiente comando:
```
export PORT=8080
```
Dentro del directorio del proyecto ejecutamos el siguiente comando:
```
gradle build
```
Si deseamos borrar rastros de despliegues anteriores realizamos el build con el siguiente comando:
```
gradle clean & gradle build
```

_Para contruir la imagen de docker_

Dentro del directiorio del proyecto ejecutamos el siguiente comando:
```
docker build --build-arg JAR_FILE=build/libs/\*.jar -t fraud-context-app .
```

_Para correr la imagen de docker generada_

Dentro del directorio del proyecto ejecutamos el siguiente comando:
```
docker run -p 8080:8080 -t fraud-context-app
```

_Probar la aplicación desde heroku_

La aplicación esta desplegada en heroku en la siguiente URL:
```
https://aqueous-coast-47895.herokuapp.com
```
Para tracear una ip utilizando curl se puede hacer con el siguiente comando:
```
curl -X POST -H "Content-Type: application/json" -d '{"ip":"83.44.196.93"}' https://aqueous-coast-47895.herokuapp.com/trace/
```
Para tracear una ip utilizando curl se puede hacer con el siguiente comando:
```
curl https://aqueous-coast-47895.herokuapp.com/stats/
``` 

## Archivos de configuración ⚙️

_Se pueden encontrar los archivos de configuración en las siguientes rutas_

Gradle:
```
./fraud-context/build.gradle
```

Docker:
```
./fraud-context/Dockerfile
```

## Pruebas externas con Postman ⚙️

_Para realizar pruebas con [Postman](https://www.getpostman.com/) se incluye una colección de invocaciones a la API_

Este archivo se encuentra en la siguiente ubicación:
```
./postman/MELI.postman_collection.json
```
Nota: La colección esta configurada para ultilizar la variable de entorno "url", es necesario setear esta varianble para realizar las pruebas. Por ejemplo, para correr las pruebas utilizando el ambiente de heroku se debe setear la variable url con el valor "https://aqueous-coast-47895.herokuapp.com".

## Construido con 🛠️

* [Spring Boot](http://spring.io/projects/spring-boot) - El framework web usado
* [Gradle](https://gradle.org/) - Manejador de dependencias
* [Docker](https://www.docker.com/) - Contenedor de aplicacion para la instalacion

## Autores ✒️

* **Marcelo Wieja** - [mdwarg](https://github.com/mdwarg)
