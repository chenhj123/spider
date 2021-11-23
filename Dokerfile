FROM java:11
EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE}/app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]