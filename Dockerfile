FROM openjdk:17-alpine
MAINTAINER "vikas verma"
WORKDIR /app
COPY target/FoodOrderingApp-0.0.1-SNAPSHOT.jar /app/foodapp.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/foodapp.jar"]