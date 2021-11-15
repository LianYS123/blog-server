#!/usr/bin/env bash
set -x
# pid=`ps -ef | grep "org.springframework.boot.loader.JarLauncher" | grep -v grep | tr -s " "|cut -d" " -f2`
# if [ -n "$pid" ];then
#   kill -9 "$pid"
#   echo -e "stop complete"
# else
#   echo -e "liuli service is not running"
# fi
pkill -f blogserver
set +x