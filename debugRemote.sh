#!/bin/bash
clear
echo "Update code"
sudo git pull

echo "Compiling code"
sudo javac -d bin -sourcepath src -cp lib/bluecove-bluez-2.1.1-SNAPSHOT.jar;bluecove-2.1.1-SNAPSHOT.jar src/bluemesh/test/TestBlueMesh.java

echo "Debug code"
sudo java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=4000,suspend=n Main

echo "Debug running...."