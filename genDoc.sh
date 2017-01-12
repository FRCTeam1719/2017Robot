#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "frcteam1719/2017robot" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
  echo -e "Building doc... \n"
  javadoc -d javadoc org.usfirst.frc.team1719.robot


  
fi
