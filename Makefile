MONGO_CONTAINER_NAME=mongodb-container
MONGO_IMAGE=mongo
MONGO_PORT=27017
MONGO_ROOT_USER=admin
MONGO_ROOT_PASS=adminpass
MONGO_DB=eventsDatabase
MONGO_USER=eventsUser
MONGO_PASS=eventsPass
MONGO_COLLECTION=eventsTest

SPRING_CONTAINER_NAME=events-container
SPRING_BUILD_CONTEXT=.
SPRING_PORT=8080

.PHONY: run start setup build-app start-app stop clean

run:
	docker-compose up --build -d

start:
	docker start $(MONGO_CONTAINER_NAME)
	docker start $(SPRING_CONTAINER_NAME)

setup:
	docker exec -it $(MONGO_CONTAINER_NAME) mongosh "mongodb://$(MONGO_ROOT_USER):$(MONGO_ROOT_PASS)@localhost:$(MONGO_PORT)" --eval '\
	  use $(MONGO_DB); \
	  db.createUser({ \
	    user: "$(MONGO_USER)", \
	    pwd: "$(MONGO_PASS)", \
	    roles: [{ role: "readWrite", db: "$(MONGO_DB)" }] \
	  }); \
	  db.createCollection("$(MONGO_COLLECTION)");'

build-app:
	./gradlew clean build
	docker build -t springboot-app-image:latest $(SPRING_BUILD_CONTEXT)

start-app:
	docker run -d --name $(SPRING_CONTAINER_NAME) -p $(SPRING_PORT):8080 springboot-app-image:latest

stop:
	docker stop $(MONGO_CONTAINER_NAME)
	docker stop $(SPRING_CONTAINER_NAME)

clean:
	docker rm -f $(MONGO_CONTAINER_NAME)
	docker rm -f $(SPRING_CONTAINER_NAME)
	docker image rm springboot-app-image:latest
