run:
	@docker docker-compose up -d
down:
	@docker compose down
test:
	mvn clean verify
clean:
	mvn clean install