# README #

This README would normally document whatever steps are necessary to get your application up and running.

### LOCAL ###

* mvn spring-boot:run

### contruir imagen docker ###

* mvn clean
* mvn install

* docker build --rm=false -t lisandropaolini/mtp-productos-service .

* docker run -p 8088:8080 -t lisandropaolini/mtp-productos-service# mt-login-service
