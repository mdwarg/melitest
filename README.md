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

* [Gradle](https://gradle.org/install/)
* [Docker](https://docs.docker.com/install/)

## Ejecutando las pruebas ⚙️

_Se pueden ejecutar las pruebas usando maven con el siguiente comando_

Dentro del directorio "./fraud-context", que es donde se encuentra el código del proyecto ejecutamos:
```
gradle test
```
## Deployment 📦

_Para desplegar el proyecto debemos hacer el empaquetado de la apicacion utilizando gradle_

Dentro del directorio "./fraud-context", que es donde se encuentra el código del proyecto ejecutamos el siguiente comando:
```
gradle build
```

## Archivos log ⚙️

_Se pueden consultar los archivos log generados por la aplicación_

Los archivos log se generan en la siguiete ruta (configurable desde application.yml):
```
/var/tmp/
```
Como la aplicación se ejecuta dentro de un contenedor docker precisamos enviar al mismo los comandos que queremos ejecutar. Lo hacemos de la siguiente forma:
```
docker exec -i {nombre del contenedor} {comando}
```
Para obtener el nombre del contenedor ejecutamos el siguiente comando:
```
docker ps
```

Ejemplo para ver el log con el comando tail:
```
docker exec -i  tail -f /var/tmp/
```

## Archivos de configuración ⚙️

_Se pueden encontrar los archivos de configuración en las siguientes rutas_

Aplicación:
```
./fraud-context/src/main/resources/config.properties
```

Spring:
```
./fraud-context/src/main/resources/application.yml
```

Gradle:
```
./fraud-context/build.gradle
```

## Pruebas externas con Postman ⚙️

_Para realizar pruebas con [Postman](https://www.getpostman.com/) se incluye una colección de invocaciones a la API_

Este archivo se encuentra en la siguiente ubicación:
```
./postman/.postman_collection.json
```

## Construido con 🛠️

* [Spring Boot](http://spring.io/projects/spring-boot) - El framework web usado
* [Gradle](https://gradle.org/) - Manejador de dependencias
* [Docker](https://www.docker.com/) - Contenedor de aplicacion para la instalacion

## Autores ✒️

* **Marcelo Wieja** - [mdwarg](https://github.com/mdwarg)
