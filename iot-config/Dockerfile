FROM java:8-jre
ADD ./target/iot-config*.jar /app/iot-config.jar
CMD ["java", "-Xmx200m", "-jar", "/app/iot-config.jar"]

EXPOSE 7001