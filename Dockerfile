ARG SERVER_PORT=8080
FROM maven:3.9.4-amazoncorretto-20-al2023

#ADD . /usr/src/app/
#WORKDIR /usr/src/app
#
#RUN chown -R java /usr/src/app
#USER java
#
#RUN javac MikoEventsApplication.java
#ENTRYPOINT ["java", "MikoEventsApplication"]

COPY . /tmp
WORKDIR /tmp
EXPOSE $SERVER_PORT
RUN mvn clean install -DskipTests
ENTRYPOINT ["mvn", "spring-boot:run"]