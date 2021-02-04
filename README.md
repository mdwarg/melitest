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

Dentro del directorio donde se encuentra el código del proyecto ejecutamos:
```
gradle test
```

## Comenzando 🚀

_Correr la aplicación_
Se puede correr la apliación corriendo el siguiente comando en el directorio del proyecto:
```
gradle bootrun
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
./src/main/resources/config.properties
```

Spring:
```
./src/main/resources/application.yml
```

Gradle:
```
./build.gradle
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
