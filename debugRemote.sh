#!/bin/bash
clear
echo "Update code"
git pull

echo "Compiling code"
javac -d bin -sourcepath src -cp lib/bluecove-bluez-2.1.1-SNAPSHOT.jar src/bluemesh/test/TestBlueMesh.java

echo "Debug code"
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=4000,suspend=n Main

echo "Debug running..."