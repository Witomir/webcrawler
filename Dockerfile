FROM openjdk:11-jdk-slim as build
ENV APP_HOME=/root/dev/webcrawler/
ENV GRADLE_USER_HOME .gradle-home
WORKDIR $APP_HOME
COPY . .
RUN ./gradlew clean build --warn

FROM openjdk:11-jre-slim
COPY --from=build /root/dev/webcrawler/build/libs/webcrawler.jar /opt/webcrawler.jar
WORKDIR /opt/

ENTRYPOINT ["java", "-jar", "/opt/webcrawler.jar", "-starting-url"]
CMD ["https://wiprodigital.com"]