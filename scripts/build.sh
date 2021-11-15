#!/usr/bin/env bash
echo 'The following Maven command installs your Maven-built Java application'
echo 'into the local Maven repository, which will ultimately be stored in'
echo 'Jenkins''s local Maven repository (and the "maven-repository" Docker data'
echo 'volume).'
set -x
mvn -B -DskipTests clean package
mvn jar:jar install:install help:evaluate -Dexpression=project.name

ssh lian "rm -rf javaspace/blog/*"
NAME=`mvn help:evaluate -Dexpression=project.name | grep "^[^\[]"`
VERSION=`mvn help:evaluate -Dexpression=project.version | grep "^[^\[]"`
scp target/"${NAME}"-"${VERSION}".jar lian:~/javaspace/blog/
set +x

