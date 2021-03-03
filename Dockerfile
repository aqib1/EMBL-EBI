FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ADD target/EMBL-EBI-0.0.1-Release.jar EMBL-EBI-0.0.1-Release.jar
ENTRYPOINT ["java", "-jar", "EMBL-EBI-0.0.1-Release.jar"]
EXPOSE 8080