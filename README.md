# TableTop reviews microservice
[![Build Status](https://travis-ci.org/TableTopLtd/tt-reviews.svg?branch=master)](https://travis-ci.org/TableTopLtd/tt-reviews)
```

## When developing

Run
```bash
mvn clean package
```
from repo root directory.

To run application local jar, run:
```bash
java -jar api/target/*.jar
```

Microservice finds database through ip

To test the service you should go to
```
http://localhost:8086/v1/reviews
```
To see a list of all reviews.

```
http://localhost:8086/v1/reviews/1
```
To see the first one, etc.

## Build docker image
```bash
docker build . -t tt-reviews:X
```

[Optional] Define your own X

## Run application in Docker
```bash
docker run -p 8088:8088 tt-reviews:X
```
