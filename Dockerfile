FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD  target/sponsormodule-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3007
ENV JAVA_OPTS="-Dspring.profiles.active=dev"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
