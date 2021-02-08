FROM gradle:jdk11-openj9

COPY src /app/src
COPY build.gradle /app
COPY settings.gradle /app

WORKDIR /app
RUN gradle build
EXPOSE 8080
CMD java -jar build/libs/TopicsHistory-1.0.0.jar