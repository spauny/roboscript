#!/usr/bin/env bash
(sleep 20 && open http://localhost:8080/roboscript/help) & mvn clean install jetty:run