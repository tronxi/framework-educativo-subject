FROM alpine/git as git
WORKDIR /repo
ADD https://api.github.com/repos/tronxi/framework-educativo-subject/git/refs/heads/develop version.json
RUN git clone https://github.com/tronxi/framework-educativo-subject.git
RUN cd framework-educativo-subject && git checkout develop

FROM maven as builder
COPY --from="git" /repo/framework-educativo-subject .
RUN mvn package spring-boot:repackage

FROM openjdk:8-alpine
ENV clave clave
ENV eureka_host http://localhost
ENV subject-service subject-service
ENV profile dev
COPY --from="builder" /target/framework-educativo-subject-0.0.1-SNAPSHOT.jar .
CMD java -jar -Dspring.profiles.active=${profile} -Djasypt.encryptor.password=${clave} framework-educativo-subject-0.0.1-SNAPSHOT.jar --eureka-host=${eureka_host} --subject-service=${subject-service}