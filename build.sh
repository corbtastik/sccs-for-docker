#!/bin/bash
if [ -z "$1" ]
then
    echo "Please provide a tag."
    echo "usage: ./build.sh sccs-for-docker:latest"
    exit 1
else
    TAG=$1
fi
cd ./server
./mvnw clean package
docker build -t ${TAG} .
cd ..
docker image ls 