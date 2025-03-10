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
