@echo off
REM start a hub with the defaults on port 4444
REM visit http://localhost:4444/grid/console to see status
REM java -jar selenium-server-standalone-3.11.0.jar -role hub
 
REM Start a hub with params configured in JSON file
REM https://github.com/SeleniumHQ/selenium/blob/master/java/server/src/org/openqa/grid/common/defaults/DefaultHub.json
java -jar selenium-server-standalone-3.11.0.jar -role hub -hubConfig DefaultHub.json
pause