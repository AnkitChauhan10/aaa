FROM openjdk:8-jdk-alpine
RUN adduser cc_user  --disabled-password
ADD  target/discountcode-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3012
ENV JAVA_OPTS="-Dspring.profiles.active=dfw -XX:+UseSerialGC -XX:+UnlockExperimentalVMOptions -XX:MinRAMPercentage=60.0 -XX:MaxRAMPercentage=90.0 -XX:+HeapDumpOnOutOfMemoryError"
USER cc_user
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
