FROM openjdk:21

WORKDIR /config

COPY .mvn/ ./.mvn
COPY  pom.xml ./
COPY mvnw ./mvnw
COPY src ./src

RUN ./mvnw dependency:resolve

EXPOSE 9500

CMD ["./mvnw", "spring-boot:run"] 

