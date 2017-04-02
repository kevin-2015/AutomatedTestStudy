@echo off
call maven_package.bat
XCOPY  target\fitnesse-features.jar ..\FitNesseRoot\fitnesseJar\extlib\  /Y
pause