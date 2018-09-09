#!/bin/sh -x

while true; do
  read -p "Install java 8?" yn
	if [$yn & [["$yn" == [Yy]*]]]; then
		sudo apt-get install sudo apt-get install openjdk-8-jdk
	fi
done

sudo apt-get update
sudo bash ./setup/mysqlSetup.sh
sudo apt-get install gradle
sudo apt-get install maven

cd initial
./gradlew bootRun
