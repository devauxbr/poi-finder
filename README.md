# POI Finder

This is a small Java 11 Spring-Boot based project that can compute density, based on a sample of points. It can be run
in a CLI or Web mode.

This project was developed following [TDD](https://en.wikipedia.org/wiki/Test-driven_development) (see `git log`) and
the [Clean Code](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship-ebook/dp/B001GSTOAM) and 
[Clean Architecture](https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164) 
guidelines from Robert C. Martin.

## Pre-requisites

- Java 11+
- Maven 3.6+

Simplest install method in UNIX environments is via [SDKMAN](https://sdkman.io/)

## Build

Simply run :
```bash
mvn clean install
```
This will run unit tests in all modules and package both `cli` and `web` runnable jars in their respective `target` folders.

## Run

### CLI

Run the CLI with :
```bash
java -jar cli/target/poi-finder-cli-0.0.1-SNAPSHOT.jar [CLI parameters]
```

#### How many POI are there in the provided zone ?

```bash
java -jar cli/target/poi-finder-cli-0.0.1-SNAPSHOT.jar --nbpoi "{\"min_lat\": 6.5, \"min_lon\": -7}"
```
Expected output :
```json
{"value":2}
```


#### Find the n densest zones

```bash
java -jar cli/target/poi-finder-cli-0.0.1-SNAPSHOT.jar --densest "{\"n\": 2}"
```
Expected output :
```json
[{"min_lat":-2.5,"min_lon":38.0,"max_lat":-2.0,"max_lon":38.5},{"min_lat":6.5,"min_lon":-7.0,"max_lat":7.0,"max_lon":-6.5}]
```

### Web

Run the Web server with :
```bash
java -jar web/target/poi-finder-web-0.0.1-SNAPSHOT.jar
```

Wait for the server to start (i.e. until `Started PointFinderWebApplication in X.YZ seconds (JVM running for A.BC)` is
displayed) then run the following commands in another terminal :

#### How many POI are there in the provided zone ?

```bash
curl http://localhost:8080/nbpoi\?min_lat\=6.5\&min_lon\=-7
```
Expected output :
```json
{"value":2}
```

#### Find the n densest zones

```bash
curl http://localhost:8080/densest/2
```
Expected output :
```json
[{"min_lat":-2.5,"min_lon":38.0,"max_lat":-2.0,"max_lon":38.5},{"min_lat":6.5,"min_lon":-7.0,"max_lat":7.0,"max_lon":-6.5}]
```
