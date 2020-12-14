# kotlin-daprservice

Install as maven project

## SUBSCRIBER ##
dapr run --components-path ./components --app-id subscriber --app-port 3000 --dapr-http-port 3005 -- \
  java -jar examples/target/dapr-service-exec.jar io.dapr.service.Subscriber -p 3000
  
## PUBLISHER ##
dapr run --components-path ./components --app-id publisher --dapr-http-port 3006 -- \
  java -jar examples/target/dapr-java-sdk-examples-exec.jar io.dapr.service.Publisher
  
## CHANGE STREAM ##  This will call one insertion for testing
dapr run --components-path ./components --app-id changestream --dapr-http-port 3007 -- \
  java -jar target/dapr-service-exec.jar io.dapr.service.ChangeStream
  
