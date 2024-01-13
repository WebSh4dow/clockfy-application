#!/bin/sh
mvn clean package && docker build -t clockyproject/clockfydocker .
docker rm -f clockfydocker || true && docker run -d -p 8080:8080 -p 4848:4848 --name clockfydocker clockyproject/clockfydocker 
