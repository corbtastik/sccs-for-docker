FROM adoptopenjdk/openjdk8:alpine-slim as builder
LABEL maintainer="github.com/corbtastik"
WORKDIR /build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sccs.jar
RUN java -Djarmode=layertools -jar sccs.jar extract

FROM adoptopenjdk/openjdk8:alpine-slim
WORKDIR /app
COPY --from=builder /build/dependencies/ ./
COPY --from=builder /build/snapshot-dependencies/ ./
COPY --from=builder /build/spring-boot-loader/ ./
COPY --from=builder /build/application/ ./
COPY entrypoint.sh ./

WORKDIR /
EXPOSE 8888
VOLUME /config
ENTRYPOINT ["sh", "/app/entrypoint.sh"]