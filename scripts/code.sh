/bin/bash

git clone https://sonisatyam92@bitbucket.org/sonisatyam92/orloungeapp.git
cd orloungeapp
mvn clean package

cd ..
mv target/orloungeapp.jar .
