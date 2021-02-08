FROM gradle:jdk11-openj9

COPY . /app
WORKDIR /app
RUN gradle build

EXPOSE 8080
WORKDIR /app

CMD java -jar build/libs/TopicHistory-1.0.0.jar