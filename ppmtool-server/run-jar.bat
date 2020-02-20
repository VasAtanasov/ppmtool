@echo off

set DB_NAME=ppmtool_db
set DB_USERNAME=root
set DB_PASSWORD=1234

mvn clean package -DskipTests && java -jar target/ppmtool-0.0.1-SNAPSHOT.jar