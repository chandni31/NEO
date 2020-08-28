# NEO

To start kafka

kafkafolder/ bin/window
cmd zookeeper-server-start.bat ../../config/zookeeper.properties
cmd kafka-server-start.bat ../../config/server.properties 

cmd kafka-run-class.bat kafka.tools.GetOffsetShell --broker-list localhost:9092,127.0.0.1:9092, --topic topic-Name --time -1


To deploy minikube

cmd docker build -t dockerUser/repo .

to run in local

cmd  docker run -p 8080:8080 dockerUser/repo


Swagger URL: http://localhost:8080/swagger-ui.html#





