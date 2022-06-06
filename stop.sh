#!/bin/bash
# created by dewan 4th june 2022



fileDate_nohup_emilservice=$( date +"nohup_emilservice_"%Y%m%d%H%M%S )
fileDate_nohup_userservice=$( date +"nohup_userservice_"%Y%m%d%H%M%S )
echo fileDate_nohup_emilservice
echo fileDate_nohup_userservice

cp nohup_emilservice.out $fileDate_nohup_emilservice
cp nohup_userservice.out fileDate_nohup_userservice
rm -rf nohup_emilservice.out
rm -rf nohup_userservice.out

PID_emilservice=$(ps -ef | grep EmailService-1.0.jar | grep -v grep | awk '{print $2}')
PID_userservice=$(ps -ef | grep UserService-1.0.jar | grep -v grep | awk '{print $2}')
echo $PID_emilservice
echo $PID_userservice

kill -9 $PID_emilservice
kill -9 $PID_userservice