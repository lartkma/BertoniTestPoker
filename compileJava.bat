@echo off
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_181
"%JAVA_HOME%\bin\javac" -cp "lib\*" -d build\classes src\com\bertoni\poker\model\*.java src\com\bertoni\poker\*.java