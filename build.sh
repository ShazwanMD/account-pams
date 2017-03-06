#!/bin/bash
echo "TOMCAT_HOME=/opt/tomcat"
echo "Stoping tomcat..."
systemctl stop tomcat
echo git pull
git pull
echo "mvn clean & tomcat clean"
mvn clean
rm -rf /opt/tomcat/webapps/account
rm /opt/tomcat/webapps/account.war
echo mvn package
mvn package -DskipTests=true
mv target/account.war target/account.war
echo copying account.war
cp target/account.war /opt/tomcat/webapps
echo Starting tomcat
echo TOMCAT_HOME=/opt/tomcat
systemctl start tomcat
echo Script Completed!
