# Apache Beam

## Primeros pasos.

Antes de empezar es necesario tener instalados los siguientes programas en el ordenador:

* JDK8 o posterior: https://openjdk.java.net/install/
* Maven: https://maven.apache.org/download.cgi
* Un editor de código: ItelliJ IDEA, Visual Studio Code, ...

Si lo prefieres, y es mucho más cómodo, puedes bajarte la imagen de Docker que hemos
preparado para el curso.
* Instalar Docker: https://docs.docker.com/install/linux/docker-ce/ubuntu/
* `docker pull villaz/uda-techfest:v1.0`

## Ejecutar código.

Para ejecutar el código sigue los siguientes pasos:
* En tu máquina local entra dentro de la carpeta del proyecto.
* `cd uda-techfest`
* Ejecuta la imagen de Docker.
* `docker run -v $PWD:/home/user/beam -it villaz/uda-techfest:v1.0`
* Una vez que estamos dentro del contenedor, para ejecutar el código hay que escribir lo siguiente:
* `mvn compile exec:java -D exec.mainClass=org.uda.techfest.numbers.SumNumbers -Pdirect-runner`
