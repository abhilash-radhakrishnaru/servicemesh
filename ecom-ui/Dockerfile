FROM openjdk:14 as ecom-ui-app
COPY target/ecom-ui.jar ecom-ui.jar
EXPOSE 9080
ENTRYPOINT ["java", "-jar", "ecom-ui.jar"]