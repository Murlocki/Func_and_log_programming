FROM openjdk:21
EXPOSE 8080:8080
RUN mkdir /app
RUN mkdir -p /app/src/main/resources
COPY ./src/main/resources /app/src/main/resources
COPY ./build/libs/*.jar /app/app.jar
WORKDIR /app
CMD ["java","-jar","app.jar"]