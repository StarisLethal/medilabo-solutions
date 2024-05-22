FROM openjdk:21

WORKDIR /discovery

COPY .mvn/ ./.mvn
COPY  pom.xml ./
COPY mvnw ./mvnw
COPY src ./src

RUN ./mvnw dependency:resolve

EXPOSE 9502

CMD ["./mvnw", "spring-boot:run"] 

