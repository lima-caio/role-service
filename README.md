# Role-Service SpringBoot + MongoDB Application (Dockerized)

Simple application to maintain Team Member Roles. <br>
Teams and Users are not managed by this service, only data is consumed.

## Architecture

### Language

* **Implementation**: Java 8
    
* **Tests**: Groovy 2.5.6
    
* **Integration Tests**: Groovy 2.5.6
    
* **Build**: Gradle 4.10.3
    
## Gradle

### Gradle Clean

Command to clean all generated files.

```bash
./gradlew clean
```

### Gradle Build

Command to build application

```bash
./gradlew build
```

The ```role-service-1.0.0.jar``` can be found on folder ```build/libs```.

### Gradle Run

Command to run the application. By default, the service is provided under 'http://localhost:7080/api/role-service'. <br>
It is required to have Mongo running on your environment.<br>

```bash
./gradlew bootRun
```

## Unit Test

### Run Unit Tests

Command to run unit tests.

```bash
./gradlew test
```
   
Reports can be found under ```build/reports/test```.

## Docker

### Role Service Image

Run Gradle Build to generate the ```role-service-1.0.0.jar```.

Then generate the role-service application docker image:

```bash
docker build -t role-service .
```
    
### Launching Role Service

```bash
docker-compose up
```

Docker will download a MongoDB image, so it is not necessary to have MongoDB running in the environment.