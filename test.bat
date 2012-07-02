@echo off
REM startujemy jetty a potem gwt development mode
start jetty.bat start
starthosted.bat && jetty.bat stop