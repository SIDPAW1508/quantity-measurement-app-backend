FROM ubuntu:24.04

ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update && \
    apt-get install -y openjdk-21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -Dmaven.test.skip=true

EXPOSE 8080

CMD ["java", "-jar", "target/quantity-measurement-app-0.0.1-SNAPSHOT.jar"]
