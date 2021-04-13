#!/bin/sh
java -cp /app ${JAVA_OPTS} org.springframework.boot.loader.JarLauncher \
--server.port=8888 \
--spring.config.name=application "$@"