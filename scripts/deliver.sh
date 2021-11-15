#!/usr/bin/env bash

filepath=$(cd "$(dirname "$0")"; pwd)

NAME=`mvn help:evaluate -Dexpression=project.name | grep "^[^\[]"`
VERSION=`mvn help:evaluate -Dexpression=project.version | grep "^[^\[]"`

# kill last process
# sshpass -p tb1766318380 ssh -T lian<"$filepath""/kill.sh"
ssh lian "pkill -f blogserver"

set -x;

if [ $? -eq 0 ]
then
  # empty workspace
  # move to server
  if [ $? -eq 0 ]
    # run
    jarName="${NAME}-${VERSION}.jar"
    then ssh lian "cd javaspace/blog && jar -xf $jarName && nohup bash -c 'exec -a blogserver java org.springframework.boot.loader.JarLauncher &'"
  fi
fi
set +x;
