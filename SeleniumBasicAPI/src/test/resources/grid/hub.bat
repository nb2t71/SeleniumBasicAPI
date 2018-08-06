set selenium-path=%__CD__:~0,-1%\selenium-server-standalone-3.11.0.jar
java -jar "%selenium-path%" -role hub -port 4444 
pause