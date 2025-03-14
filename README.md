### demo-events

# Technologies
This demo is based in a Springboot application with a integration with Mongo DB

# Get Provider Info
For get the information from provider, as a normal endpoint in which is not a Webhook or is not a some Pubsub that you can subscribe,
I make Schedule to periodically get info from the endpoint and store it in Mongo db(checking new Events)

Also for error from the endpoint it would be nice to have some log tracker(I only implement the normal System.out)

# Endpoint for get events from start -end date
 Link running: http://localhost:8080/swagger-ui/index.html#/events-controller/getEventsByRangeOfDates
 ![imagen](https://github.com/user-attachments/assets/f002485f-885c-405b-9a75-f609db2c69b9)

This enpoint is configured basically with a query to Mongo DB that is more efficient and Mongo is prepared for big queries
and it would be nice to implement some Cache with redis or whatever to more efficiency

# Scalability
I couldn't have time for integrated but I have in mind to use for Springboot Eureka Client and Eureka server for Load Balancing
or if our app is in the Cloud use Cloud Load Balancing.

I add Hiraki for better performance in connections pool

# Makefile
I create file for bring the docker image of Mongo DB and configure it also for the compilation of the app and running

# Test
I add test only for the DateHelper

# For a better aproach to Hexagonal Architecture
The provided README.md file does not give a complete view of the project's structure, but based on the information given, here are some potential issues that might not align with hexagonal architecture principles:  
## Direct Integration with MongoDB:  
 The project directly integrates with MongoDB without an abstraction layer. In hexagonal architecture, the database should be accessed through a repository interface, which is implemented by an adapter.
## System.out Logging:  
 Using System.out for logging is not ideal. Hexagonal architecture encourages the use of interfaces for logging, which can be implemented by different logging frameworks.
## Lack of Clear Separation of Concerns:  
 The description does not mention clear separation between the core business logic and the infrastructure concerns (like scheduling and database access). Hexagonal architecture emphasizes separating the core application logic from external systems.
## Direct Scheduling Logic:  
 The scheduling logic to periodically get info from the endpoint and store it in MongoDB should be part of an adapter, not the core business logic.
## No Mention of Ports and Adapters:  
 There is no mention of using ports (interfaces) and adapters (implementations) to interact with external systems like databases, web services, etc.
## To align with hexagonal architecture, consider the following improvements:  
 - Introduce Repository Interfaces: Define repository interfaces in the core domain and implement them in the infrastructure layer.  
 - Use Dependency Injection: Ensure that dependencies like repositories and services are injected into the core application logic.  
 - Abstract Logging: Use a logging interface and inject a logging implementation.  
 - Separate Scheduling Logic: Move scheduling logic to an adapter that interacts with the core application logic through a defined interface.
