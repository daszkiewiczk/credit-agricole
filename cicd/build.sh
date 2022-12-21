#!/bin/bash
cd loan-calculator-app/loan-calculator-backend
docker run --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project gradle gradle build
