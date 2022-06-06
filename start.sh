#!/bin/sh
# created by dewan 4th june 2022
#which docker-compose

#docker-compose -f docker-compose.yml up -d
sleep 10s
#rm -rf appLok.lock
nohup /usr/lib/jvm/java-8-openjdk-amd64/bin/java -Xmx1024 -jar EmailService-1.0.jar >> nohup_emilservice.out 2>&1 &
sleep 10s
nohup /usr/lib/jvm/java-8-openjdk-amd64/bin/java -Xmx1024 -jar UserService-1.0.jar >> nohup_userservice.out 2>&1 &
