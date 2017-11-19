FROM java:8-jre
ADD ./target/iot-config*.jar /app/iot-config-test.jar
CMD ["java", "-Xmx200m", "-jar", "/app/iot-config-test.jar"]

EXPOSE 7002