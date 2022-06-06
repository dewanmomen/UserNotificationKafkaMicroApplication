FROM java:8
MAINTAINER momendewan@gmail.com
#EXPOSE 8081
# add files to image
#ADD /CommonDTO/target/CommonDTO-1.0-SNAPSHOT.jar CommonDTO-1.0.jar
#ADD /MailNotificationService/target/EmailService-1.0-SNAPSHOT.jar EmailService-1.0.jar
#ADD /UserService/target/UserService-1.0-SNAPSHOT.jar UserService-1.0.jar

#COPY: We let Docker copy our jar file into the image.
COPY /CommonDTO/target/CommonDTO-1.0-SNAPSHOT.jar CommonDTO-1.0.jar
COPY /MailNotificationService/target/EmailService-1.0-SNAPSHOT.jar EmailService-1.0.jar
COPY /UserService/target/UserService-1.0-SNAPSHOT.jar UserService-1.0.jar

#COPY docker-compose.yaml docker-compose.yaml
#CMD docker-compose up -d

#COPY my_wrapper_script.sh my_wrapper_script.sh
COPY start.sh start.sh
COPY stop.sh stop.sh
RUN chmod +x start.sh
RUN chmod +x stop.sh
#ADD start.sh /
#RUN ./start.sh
#CMD ./start.sh
#ENTRYPOINT ["/bin/bash", "./start.sh"]
ENTRYPOINT ["java", "-jar","UserService-1.0.jar", "&"]
ENTRYPOINT ["java", "-jar","EmailService-1.0.jar", "&"]

