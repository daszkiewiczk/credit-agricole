#!/bin/bash

cd ../loan-calculator-app/loan-calculator-backend || return
docker run --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project gradle gradle clean build --no-daemon
docker build . --tag daszkiewiczk/loan-calculator-backend:latest


cd ../loan-calculator-frontend || return
docker build . -t daszkiewiczk/loan-calculator-frontend:latest

kind load docker-image daszkiewiczk/loan-calculator-backend:latest --name kazik
kind load docker-image daszkiewiczk/loan-calculator-frontend:latest --name kazik

