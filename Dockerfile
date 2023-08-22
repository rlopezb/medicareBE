FROM bitnami/tomcat:latest
ENV TOMCAT_PASSWORD=medicare
ENV TOMCAT_USERNAME=medicare
COPY ./target/ROOT.war /opt/bitnami/tomcat/webapps