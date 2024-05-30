 #!/usr/bin/env bash

 mvn clean package

 echo 'Copy files...'

 scp target/ScheduleOfEvents-0.0.1-SNAPSHOT.jar \
     root@80.68.156.54:/home

 echo 'Restart server...'

 ssh  root@80.68.156.54  << EOF

 pgrep java | xargs kill -9
 nohup java -jar ScheduleOfEvents-0.0.1-SNAPSHOT.jar  > log.txt &

 EOF

 echo 'Bye'