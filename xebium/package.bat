@echo off
call maven_package.bat
XCOPY  target\xebium-0.14-SNAPSHOT.jar ..\FitNesseRoot\fitnesseJar\xebiumlib\  /Y

del /F /Q ..\FitNesseRoot\fitnesseJar\xebiumlib\fitnesse-20161106.jar
pause