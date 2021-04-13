# sccs-for-docker

Spring Cloud Config Server as a container image.

Builds a specific version of SCCS using:

* Docker
* Java on development environment
  * AdoptOpenJDK 1.8 javac
* Spring Cloud (current version [v2020.0.2](https://github.com/spring-cloud/spring-cloud-release/releases/tag/v2020.0.2))
  * [All Spring Cloud releases](https://github.com/spring-cloud/spring-cloud-release/)

Container runtime
  * [Alpine-Linux Slim base image](https://github.com/AdoptOpenJDK/openjdk-docker/blob/master/8/jdk/alpine/Dockerfile.hotspot.releases.slim)
  * [AdoptOpenJDK - openjdk8](https://hub.docker.com/r/adoptopenjdk/openjdk8)

## Build

```bash
# ./build.sh TAG
./build.sh sccs:latest
```

## Run

```bash
docker run -it --name=sccs \
      -p 8888:8888 \
      -v config:/config \
      sccs:latest
```
