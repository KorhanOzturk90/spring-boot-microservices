### Spring Boot Microservices Demo

Simple demo of service registration and discovery using Eureka and RabbitMQ.



### Try it out

#### Standalone Service

Switch to standalone service directory and use gradle to build and run the service

```
cd standalone
gradle build
gradle bootRun
```

This service will expose a public endpoint `greetings` and a managed enpoint `internal-greetings` both returning dummy responses.

#### Microservices communicating over HTTP via Eureka Server

#####Start the server

```
cd eureka-server
mvn spring-boot:run
```

Visit the Eureka dashboard at http://localhost:8761

Note that there are no 'Instances' yet registered



#####Start up the first client (accounts-service)
Use maven to build and start the service (can also be achived via Gradle)

```
cd accounts-service
mvn spring-boot:run
```

Visit the client directly at http://localhost:8090/

The `/accounts/{account-number}` endpoint will return a dummy response


Eureko dashboard on `http://localhost:8761/` will automatically get updated and show 
`accounts-service` as the registered service. 

#####Start up the second client (accounts-web-service)

Use maven to build and start the dummy web service (can also be achived via Gradle)
which talks to account-service over Eureko to get dummy account info 

```
cd accounts-web-service
mvn spring-boot:run
```
This service exposes a single dummy endpoint `/accounts/{account-number}` on port 8091
and makes a call to `accounts-service` to get sample account String response.

You can also visit the Eureka dashoboard again now and see it listed there.


#### Microservices communicating over Messaging Queue (RabbitMQ)

#####Prerequisites
Install a local RabbitMQ server and run it (uses port 15672 by default)

```$xslt
brew install rabbitmq
rabbitmq-server
```

This project comprises of a simple message receiver and publisher that communicates over a message AMQP queue.

The exchange() method creates a topic exchange. The binding() method binds these two together, defining the behavior that occurs when RabbitTemplate publishes to an exchange

It can be run simply by gradle spring boot plugin `gradle bootRun`


