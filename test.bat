@echo off
REM szybka kompilacja, startujemy jetty a potem gwt w development mode

call draft.bat
start jetty.bat start
starthosted.bat & jetty.bat stop