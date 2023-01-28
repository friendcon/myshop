#!/usr/bin/env bash

REPOSITORY=/home/ec2-user/myshop/deploy
APP_NAME=myshop

cp $REPOSITORY

JAR_NAME=$(ls $REPOSITORY/build/libs | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(sudo lsof -t -i:8080)
echo "> 현재 구동중인 애플리케이션 PIT : $CURRENT_PID"

if [ -z $CURRENT_PID ];
then
  echo "> 종료 할 프로세스가 없습니다"
else
  echo "> kill -15 $CURRENT_PID"
  sudo kill -15 $CURRENT_PID
  sleep 5
fi

echo ">nohup.out 권한 설정"
sudo chmod 755 nohup.out

echo "> $JAR_PATH 배포"
sudo nohup java -jar $JAR_PATH > $REPOSITORY/nohup.out 2>&1 &



