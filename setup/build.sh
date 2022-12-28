#!/bin/bash

cd ../loan-calculator-app/loan-calculator-backend || return
docker run --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project gradle gradle clean build --no-daemon
docker build . --tag daszkiewiczk/loan-calculator:backend


cd ../loan-calculator-frontend || return
docker build . -t daszkiewiczk/loan-calculator:frontend

kind load docker-image daszkiewiczk/loan-calculator:frontend --name kazik
kind load docker-image daszkiewiczk/loan-calculator:backend --name kazik

