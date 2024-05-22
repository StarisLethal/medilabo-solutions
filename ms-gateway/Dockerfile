FROM openjdk:21

WORKDIR /gateway

COPY .mvn/ ./.mvn
COPY  pom.xml ./
COPY mvnw ./mvnw
COPY src ./src

RUN ./mvnw dependency:resolve

EXPOSE 9504

CMD ["./mvnw", "spring-boot:run"] 

