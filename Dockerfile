# syntax=docker/dockerfile:1
FROM alpine:3 as git-fetcher
USER root

RUN apk add git

RUN git clone https://github.com/RUB-Informatik-im-Bauwesen/OpenBimRL.git /app
RUN cd /app && git checkout 9699b39 && rm -rf .git

FROM aecgeeks/ifcopenshell:latest as binaries

FROM maven:3.9.6-amazoncorretto-21-debian-bookworm
USER root

COPY --from=binaries /usr/include/ifcparse /usr/include/ifcparse
COPY --from=binaries /usr/lib/libIfcParse.a /usr/local/lib/libIfcParse.a

COPY --from=git-fetcher /app /build/api

RUN apt update && apt install -y libboost-dev clang make

COPY . /build/engine
WORKDIR /app

RUN cd /build/api     && mvn install
RUN cd /build/engine  && mvn package -Dmaven.test.skip -X  # build (and test package [in the future...])

RUN bash -c "cp /build/engine/target/*-jar-with-dependencies.jar app.jar"
RUN cp /build/engine/src/main/resources/lib.so .

RUN rm -rf /build

CMD java -jar app.jar