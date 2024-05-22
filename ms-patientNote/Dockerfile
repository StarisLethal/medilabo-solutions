FROM openjdk:21

WORKDIR /patientNote

COPY .mvn/ ./.mvn
COPY  pom.xml ./
COPY mvnw ./mvnw
COPY src ./src

RUN ./mvnw dependency:resolve

EXPOSE 9565

CMD ["./mvnw", "spring-boot:run"] 

