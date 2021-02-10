/bin/bash

java -jar orloungeapp.jar -Dserver.port=8080 -Dspring.profiles.active=aws > /home/ubuntu/logs/app.log 2>&1 &