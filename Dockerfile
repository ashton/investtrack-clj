FROM java:openjdk-8-jre

ENV PORT 8080
EXPOSE 8080

ADD target/investtrack-*-SNAPSHOT-standalone.jar /root/app.jar

CMD ["java", "-jar", "/root/app.jar"]