FROM tomcat:8.0

MAINTAINER Carlos Arenas <>

WORKDIR /usr/local/tomcat

ADD target/*.war webapps/user-profile-service.war

ADD tomcat-config/settings.xml /usr/local/tomcat/conf/

ADD tomcat-config/tomcat-users.xml /usr/local/tomcat/conf/

EXPOSE 8080

CMD ["catalina.sh", "run"]