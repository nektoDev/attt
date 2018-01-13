FROM java:8-alpine
VOLUME /tmp
EXPOSE 8080

ENV DB_HOST ${DB_HOST}
ENV TRANSMISSION_HOST ${TRANSMISSION_HOST}

ADD attt-*.jar attt.jar
RUN sh -c 'touch /attt.jar'
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod -Ddb.host=$DB_HOST -Dtransmission.host=$TRANSMISSION_HOST -jar /attt.jar
