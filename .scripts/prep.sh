#!/bin/bash

#Download libraries
echo -e "Downloading libs"
cd $HOME
wget http://104.131.160.86/libpackage.tar.gz
echo -e "Unpacking libs"
tar -xzf libpackage.tar.gz
