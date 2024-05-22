FROM openjdk:21

WORKDIR /frontend

COPY .mvn/ ./.mvn
COPY  pom.xml ./
COPY mvnw ./mvnw
COPY src ./src

RUN ./mvnw dependency:resolve

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"] 

