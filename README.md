# Togglz Demo

Implementing the feature toggles code pattern using the [Togglz Library](https://www.togglz.org/).

This implementation contains:
- **togglz-server**: used for defining config around the togglz, as well as holding the 
[togglz admin console](https://www.togglz.org/documentation/admin-console) to enable switching toggles on and off manually.
- **togglz-client**: demonstrates how to access the toggles in a service.
- **Redis**: Used to store the state of each toggle.


## Build / Deploy

1. Run `docker compose up -d` to run Redis and Redis Commander
2. `cd` into both `togglz-client` and `togglz-server` in separate terminal windows and run `mvn spring-boot:run`
3. Services can be found at following URLs:
   - Redis Commander: https://localhost:8081
   - togglz-server: https://localhost:8082
     - `/toggle/all?metadata=true` to view all toggle states
     - `/toggle/{toggleName}?metadata=true` to view a toggles' state
     - `/togglz-console/index` to view the admin UI
   - togglz-client: https://localhost:8083
     - `/toggle/all?metadata=true` to view all toggle states
     - `/toggle/{toggleName}?metadata=true` to view a toggles' state
     - `/basic-test` to show simple feature toggle test
