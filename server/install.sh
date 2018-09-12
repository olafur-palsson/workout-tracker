#!/bin/sh -x

currentFolder=$PWD

read -p "Install java 8?" yn
if [$yn & [[$yn == [Yy]*]]]; then
	sudo apt-get install openjdk-8-jdk
fi

sudo apt-get update

cd setup
sudo bash ./mysqlSetup.sh
cd $currentFolder


sudo apt-get install gradle
sudo apt-get install maven

