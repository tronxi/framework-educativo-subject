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
ENV subject_service subject-service
ENV profile dev
ENV rabbit_host localhost
ENV rabbit_pass guest
ENV subject_db localhost
COPY --from="builder" /target/framework-educativo-subject-0.0.1-SNAPSHOT.jar .
CMD java -jar -Dspring.profiles.active=${profile} -Djasypt.encryptor.password=${clave} framework-educativo-subject-0.0.1-SNAPSHOT.jar --eureka-host=${eureka_host} --subject-service=${subject_service} --subject-db=${subject_db} --spring.rabbitmq.host=${rabbit_host} --spring.rabbitmq.password=${rabbit_pass}