FROM openjdk:17-jdk
MAINTAINER "vikas verma"
WORKDIR /app
COPY target/FoodOrderingApp-0.0.1-SNAPSHOT.jar /app/foodapp.jar
EXPOSE 8080
CMD ["java", "-jar", "foodapp.jar"]