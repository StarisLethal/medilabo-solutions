FROM openjdk:21

WORKDIR /authserv

COPY .mvn/ ./.mvn
COPY  pom.xml ./
COPY mvnw ./mvnw
COPY src ./src

RUN ./mvnw dependency:resolve

EXPOSE 8081

CMD ["./mvnw", "spring-boot:run"] 

