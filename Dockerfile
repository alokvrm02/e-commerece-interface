FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/eCommerceInterface-1.0-SNAPSHOT.jar /app/eCommerceInterface-1.0-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "eCommerceInterface-1.0-SNAPSHOT.jar"]