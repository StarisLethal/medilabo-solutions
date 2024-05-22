FROM openjdk:21

WORKDIR /patient

COPY .mvn/ ./.mvn
COPY  pom.xml ./
COPY mvnw ./mvnw
COPY src ./src

RUN ./mvnw dependency:resolve

EXPOSE 9560

CMD ["./mvnw", "spring-boot:run"] 

