FROM tomcat:8.5-jdk8-openjdk


COPY src/main/resources/hibernate.cfg.xml /usr/local/tomcat/conf/hibernate.cfg.xml


COPY ./target/clockfydocker.war /usr/local/tomcat/webapps/