/bin/bash

ps -ef | grep orloungeapp | grep :8080 | grep -v grep | awk '{print $2}' | xargs kill
