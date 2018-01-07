FROM java:8-alpine
VOLUME /tmp
EXPOSE 8080
ADD attt-*.jar attt.jar
RUN sh -c 'touch /attt.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar","/attt.jar"]
