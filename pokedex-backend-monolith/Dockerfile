FROM maven:3.8.3-openjdk-17

WORKDIR .
COPY . .
RUN mvn clean install -DskipTests=true

CMD mvn spring-boot:run