FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR  /app
COPY . .
RUN mvn clean:clean && mvn install


FROM openjdk:21

WORKDIR  /app
COPY --from=build /app/target/Marjane.war app.war

RUN mkdir -p /app && cd /app && jar -xvf /app/app.war
RUN rm /app/app.war

EXPOSE 8082

CMD ["java", "-cp", "/app/WEB-INF/lib/*:/app/WEB-INF/classes", "com.marjane.Marjane"]