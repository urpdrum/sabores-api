build:
	mvn compile

unit-test:
	@echo "executando testes unitários"
	@mvn test

integration-test:
	@echo "executando testes de integração"
	@mvn test -P integration-test

system-test:
	@mvn test -P system-test

test: unit-test integration-test

start-app:
	@mvn spring-boot:start

package:
	@mvn package

docker-build:
	@docker build -t restaurantes-api:dev -f ./Dockerfile

docker-start:
	@docker compose -f docker-compose.yaml up -d

docker-stop:
	@docker compose -f docker-compose.yaml down