FROM java:8-jre
ADD ./target/iot-servercenter*.jar /app/iot-servercenter.jar
CMD ["java", "-Xmx200m", "-jar", "/app/iot-servercenter.jar"]

EXPOSE 8761