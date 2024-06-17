clean-package:
	./mvnw clean package -DskipTests

test:
	./mvnw test $(args)

run-create-task:
	java -jar target/dehn-backend-task-manager-0.0.1-SNAPSHOT.jar create-task $(args)

run-list-tasks:
	java -jar target/dehn-backend-task-manager-0.0.1-SNAPSHOT.jar list-tasks $(args)

run-update-task:
	java -jar target/dehn-backend-task-manager-0.0.1-SNAPSHOT.jar update-task $(args)