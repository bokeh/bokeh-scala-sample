#!/bin/bash

JVM_OPTS="-Xss8M -Xmx1G -XX:MaxPermSize=1024M"

SBT_VERSION=$(cat project/build.properties | grep sbt.version | cut -d'=' -f2)
SBT_LAUNCHER="$(dirname $0)/project/sbt-launch-$SBT_VERSION.jar"

if [ ! -e "$SBT_LAUNCHER" ];
then
    URL="http://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/$SBT_VERSION/sbt-launch.jar"
    wget -O $SBT_LAUNCHER $URL
fi

java $JVM_OPTS -jar $SBT_LAUNCHER $*
