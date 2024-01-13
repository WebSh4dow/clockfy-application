# Build
mvn clean package && docker build -t clockyproject/clockfydocker .

# RUN

docker rm -f clockfydocker || true && docker run -d -p 8080:8080 -p 4848:4848 --name clockfydocker clockyproject/clockfydocker 