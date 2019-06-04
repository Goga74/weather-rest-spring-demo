# Weather demo test service
by Igor Zamiatin, capra.lanigera@gmail.com

# Usage example:
localhost:8080/weather?ip=212.142.110.110

## To build please run: 
```
gradlew clean build
```

## To run:
```
gradlew bootRun
```
## To jar:
```
gradlew bootJar
```

## Database configuration:
Used MySql connection to locally installed MySQL,
connection properties in file:
````
weather-demo\src\main\resources\application.properties
````
Script with database creation placed in root folder 
of the project (i am not sure that i have time to implement
FlyWay versioning which i wanted)

For access to database with user `springuser`:
1. should to create user
2. should to give access to user like this:
````
grant select, insert, delete, update on weather.* to 'springuser'@'localhost';
````

### regarding caching:
It's possible to use MySQL as a cache server also with
type of table == MEMORY.

Just change the type of table and all your data in memory.
 