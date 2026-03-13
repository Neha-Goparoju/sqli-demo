# SQL Injection Attack Demonstration and Prevention

This project demonstrates how SQL Injection attacks work in a vulnerable login system and how they can be prevented using secure coding techniques.

## Technologies Used
- Java
- Spring Boot
- MySQL
- HTML & CSS
- Maven

## Features
- Login system connected to a MySQL database
- Demonstration of SQL Injection authentication bypass
- Detection of malicious SQL payloads
- Secure authentication using PreparedStatement
- Successfully detects and blocks multiple SQL injection payload variations

## Example SQL Injection Payloads Tested
admin' --

admin' OR '1'='1

admin' #

admin" OR "1"="1

admin' UNION SELECT 1,2 --

admin' OR SLEEP(5) --

The system successfully detects these payloads and prevents authentication bypass.

## Secure Query Implementation
```java
PreparedStatement ps = conn.prepareStatement(
"SELECT * FROM users WHERE username=? AND password=?");
