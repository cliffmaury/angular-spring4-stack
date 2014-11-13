angular-spring4-stack
=====================

Build a new stack STEP BY STEP (see commits) from scratch with the following features :

Server side :

* Full Java Configuration (no XML configuration neither web.xml)
* Spring MVC 4
* Spring Security 4.0.0.M2 (with the new spring-security-test module)

* Spring Data JPA 1.7
* JPA 2.1 Hibernate 4.3
* Support for Embedded Database : HSQLDB (TEST, DEV)
* Support for Server Databases : HSQLDB (server mode), MYSQL 5.6 (PROD)
* Hikari Connection Pool with MySQL

* Bean Validation 1.1 Hibernate Validator 5.1.3

* Google Guava 18.0

* Manage Spring profiles : TEST, DEV, PRODUCTION
* Configure Datasource according to active profile

* Unit/Functional tests for REST Controllers (MockMvc and RestTemplate), Security, Services, Repositories

* AJAX Authentication for REST calls

Client Side :

* (SOON) AngularJS 1.3
* (SOON) Authentication with AngularJS
* (SOON) Integrate ui-router

Development mode
=====================

* Install Node.JS on workstation
* In src/main/webapp directorory, run commands :
```
$src/main/webapp>bower install
$src/main/webapp>npm install
```

* Run grunt server :
```
$src/main/webapp>grunt server
```

* Run Java backend (from project's root):
```
$>mvn jetty:run
```