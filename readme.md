# PMS [Pharmacy Management System]

This is a pharmacy management system to simply organize interaction in pharmacy.

This is the backend part with spring boot and JWT tokens.

This project is meant to be integrated with front - end part.

**Implementation**
- CRUDs 
    * Address
    * Authority 
    * Category
    * Customer
    * Medicine
    * Supplier
    * User
    * Transaction
- Services
    * Authentication
    * Notification
    * Invoice 

**Requirements**
- JAVA (+8)
- Maven
- MySQL

**Steps to run**
- install JDK
- create database with name `pharmacy` on port 3306
- run
    ```bash
    $ mvn dependency:resolve 
    $ mvn spring-boot:run
    ```
