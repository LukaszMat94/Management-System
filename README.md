# Management-System

## Table of contents
* [Introduction](#introduction)
* [Project Status](#project-status)
* [Technologies/Tools](#technologiestools)
* [Prerequisites](#prerequisites)
* [Setup](#setup)
* [Performance of Application](#performance-of-application)
* [What's been done](#whats-been-done)
* [Incoming updates](#incoming-updates)
* [Contact](#contact)

## Introduction
Management-System is simple project based on Java language with Spring Framework to manage employees. Employees are specified by job which they perform and role to specify their permissions in system (currently, only roles are specified -> permissions will be added in next updates of system). Additional each employee can have assigned one laptop/account. 

## Project Status
Still in progress with incoming updates (new features, develop system, remove defects)
	
## Technologies/Tools
Project is created with:
* Java: 8
* Spring WebMvc: 5.2.18.RELEASE
* Spring Data JPA: 2.3.9.RELEASE
* Hibernate Core: 5.5.8.Final
* Hibernate Validator: 6.0.13.Final
* Jackson (Databind/Core/Annotations): 2.12.5
* MSSQL Driver: 9.4.0.jre16
* Liquibase Core: 4.7.1
* SpringDoc OpenApi UI: 1.5.2
* Slf4j Api: 1.7.36
* Log4j (Core/Api/Slf4j Impl): 2.16.0
* MapStruct: 1.4.2.Final

Additional tools:
* IntelliJ IDEA 2021
* Microsoft SQL Server Management Studio 2018
* Apache Maven 3.8.2
* Tomcat 9.0.54
* Git 2.35.1

## Prerequisites
The necessary prerequisites to run this system:
	* IntelliJ IDEA 2021 (Java 8 version)
  * Microsoft SQL Server Management Studio 2018
  * installed and configured Apache Maven 3.8.2
  * installed and configured Tomcat 9.0.54
 
## Setup
Before run this project, make sure that:

1. url, database, username and password is correctly setted in liquibase properties/datasource bean config:

    a) liquibase properties
      * url=jdbc:sqlserver://your_sql_url;databaseName=Management System
      * username=your_username
      * password=your_password
  
    b) the same scheme of work in datasource bean config:
      * dataSource.setUrl("jdbc:sqlserver://your_sql_url;databaseName=Management System");
      * dataSource.setUsername("your_username");
      * dataSource.setPassword("your_password");

2. install it locally using terminal:
```
$ mvn clean install
```

3. install example data into database:
```
$ mvn liquibase:update
```

## Performance of Application - examples

### CRUD example - Post operation
![Post operation](/assets/images/post.jpg)

### Exception handling example - data not found
![Exception datanotfound](/assets/images/datanotfound.jpg)

### Validation data example - login pattern
![Valdation login pattern](/assets/images/login.jpg)

## What's been done
This project contains:
 - basic configuration Spring with @Beans
 - connection with mssql to store data
 - CRUD operations with Spring Framework
 - validation of data with custom error response (with custom messages of validators)
 - store sensitive data with encryption in database (password)
 - relations One-to-One, Many-to-One (One-to-Many), Many-to-Many
 - response models schema as DTO objects
 - liquibase feature to simple manage database changes with Maven plugin
 - logging feature to log errors as rolling file

## Incoming updates

For improvement:
- Avoid cycle references when using mapstruct(Task <-> Employee) - currently ignored references in DTO objects 
- Create custom validation for attribute ZoneDateTime - currently no validation
- Optional: migrate spring/springdoc and others libraries to newer versions

Incoming Features:
- Add Spring Security: 
  * add login ui with authorization/authentication 
  * replace temporary AES256 with BCrypt encoding+matching
- Add files-management for Tasks and status of their progress (service files with .pdf extension)
- Add front-ui designed with HTML/CSS/JS (in progress) instead of current presentation (Swagger)

## Contact
Created by Łukasz Matusik - feel free to [contact](https://github.com/LukaszMat94) me!
