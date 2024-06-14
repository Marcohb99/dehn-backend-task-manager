clean-package:
	./mvnw clean package -DskipTests

test:
	./mvnw test $(args)

run-create-task:
	java -jar target/dehn-backend-task-manager-0.0.1-SNAPSHOT.jar create-task $(args)