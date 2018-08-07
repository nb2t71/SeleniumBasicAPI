@echo off
REM start a node configured by the node.json
REM https://github.com/SeleniumHQ/selenium/blob/master/java/server/src/org/openqa/grid/common/defaults/DefaultNodeWebDriver.json
java -jar -Dwebdriver.gecko.driver=..\drivers\geckodriver.exe -Dwebdriver.chrome.driver=..\drivers\chromedriver.exe selenium-server-standalone-3.11.0.jar -role node -nodeConfig DefaultNodeWebDriver.json
pause