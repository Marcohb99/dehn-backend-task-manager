clean-package:
	./mvnw clean package -DskipTests

test:
	./mvnw test $(args)