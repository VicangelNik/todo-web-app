This is a skeleton of Spring Boot application which should be used as a start point to create a working one.
The goal of this task is to create simple web application which allows users to create TODOs. In addition, the
application should expose public REST API in order to allow to manipulate data programmatically.

Below you may find a proposition of the DB model:

![DB model](DBModel.png)

To complete the exercises please implement all missing classes and functionality in order to be able to store and
retrieve information about tasks and their categories.
Once you are ready, please send it to me (ie link to your git repository) before our interview.

### Project Information

The project demonstrates:

1. A 3 layered architecture keeping in mind extendability/maintainability
2. Mapping dtos/entities between layers using mapstruct
3. Error handling via global handler (spring common way)
4. Declaration and manipulation of entities with one-to-many (bidirectional) relationship (jpa)
5. Inserting db data on spring startup
6. Utilizing Hibernate Validation

### How To Run and Build the Application

Build: ```./gradlew clean build```\
Run: ```./gradlew bootRun```\
Run in debug mode: ```./gradlew bootRun --debug-jvm```\
But in order for the application to accept a request, the listener needs to be attached in the process. \
Ofcourse application can be run and debug via intellij configuration.
