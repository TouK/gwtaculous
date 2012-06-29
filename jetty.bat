@echo off

if "%1" == "start" goto :startJetty
if "%1" == "stop" goto :stopJetty

echo Unknown parameter, use "jetty start" or "jetty stop". Launching default jetty start task...

:startJetty
	cd gwtaculous-example
	mvn jetty:run-exploded & cd..
	goto :eof

:stopJetty
	cd gwtaculous-example
	mvn jetty:stop & cd..
	goto :eof