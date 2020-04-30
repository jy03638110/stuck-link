FROM java:8

MAINTAINER octopus

RUN mkdir -p /stuck-link

WORKDIR /stuck-link

EXPOSE 80

ADD ./stuck-link-redirect/target/stuck-link-redirect.jar ./

CMD sleep 30;java -Djava.security.egd=file:/dev/./urandom -jar stuck-link-redirect.jar