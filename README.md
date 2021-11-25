# File Upload

> Java Spring Boot Project

# Initialize
Under src/main/resources folder, open application.properties, update lines.
>For PostgreSQL
```bash
spring.datasource.url= ...
spring.datasource.username= ...
spring.datasource.password= ..

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect
```

>For MySQL

```bash
spring.datasource.url= jdbc:mysql://localhost:3306/file_demo?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username= ...
spring.datasource.password= ...

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update
```

Run following SQL insert statements:
```bash
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```


open your browser : http://localhost:8081

#Database
file_demo



# Run & Test

Run Spring Boot application with command: mvn spring-boot:run

Swagger http://localhost:8081/swagger-ui.html

Postman collection:  src/main/resource/static/collection
