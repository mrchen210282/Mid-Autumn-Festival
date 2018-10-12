FROM java
VOLUME /tmp
ADD bkc-eureka-1.0-SNAPSHOT.jar eureka.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka.jar"]