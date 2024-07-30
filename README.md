# Login Register Example For Spring Boot,

### Thymeleaf, Maven or Gradle, PostgreSQL or MySQL, JPA, Spring Security, SecurityFilterChain

In this project, we are using 2 types of roles - ADMIN and USER, for adding more roles check `Role.java`

Learn to Implement this project: [Tutorial - Login Register in Spring Boot](https://studygyaan.com/spring-boot/login-register-example-using-spring-boot)

application.properties (change database settings)
```
#-------------------- server properties ---------------
server.port=8080
server.error.include-message=always

#--------------------- Logging ------------------
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type=TRACE
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate=ERROR

#--------------------- DB Connection ------------------
spring.datasource.url=jdbc:postgresql://localhost:5432/demo
spring.datasource.username=demo
spring.datasource.password=password

#--------------------JPA-ORM Properties-----------------
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```

Spring Security *SecurityFilterChain* : [How to Fix WebSecurityConfigurerAdapter Deprecated](https://studygyaan.com/spring-boot/solve-websecurityconfigureradapter-deprecated)

## References
* [Spring Boot User Login and Register Example](https://studygyaan.com/spring-boot/login-register-example-using-spring-boot)
* [Baeldung Spring Security Login](https://github.com/eugenp/tutorials/tree/master/spring-security-modules/spring-security-web-login)
* [Granted Authority Versus Role in Spring Security](https://www.baeldung.com/spring-security-granted-authority-vs-role)
* [Extra Login Fields with Spring Security](https://www.baeldung.com/spring-security-extra-login-fields)
* [Binding a List in Thymeleaf](https://www.baeldung.com/thymeleaf-list)
* [Spring Boot Registration and Login with MySQL Database Tutorial - Updated for Spring Boot 3 and Spring Security 6](https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial)
* [Using HTML select options with Thymeleaf](https://www.wimdeblauwe.com/blog/2021/04/16/using-html-select-options-with-thymeleaf/)
* [Spring Thymeleaf Form Multi-Checkboxes Mapping with Collection Example](https://www.codejava.net/frameworks/spring-boot/spring-thymeleaf-form-multi-checkboxes-mapping-with-collection-example)
* [How to implement Multi Select Drop Down with checkbox in Thymeleaf and display selected values to Spring controller](https://stackoverflow.com/questions/54168972/how-to-implement-multi-select-drop-down-with-checkbox-in-thymeleaf-and-display-s)
* [How to fill out fields in one form for multiple objects in Thymeleaf?](https://stackoverflow.com/questions/42797263/how-to-fill-out-fields-in-one-form-for-multiple-objects-in-thymeleaf#42813658)
* [How to bind an object list with thymeleaf?](https://stackoverflow.com/questions/36500731/how-to-bind-an-object-list-with-thymeleaf)
* [Data Mapping with Spring’s @ModelAttribute Annotation](https://medium.com/@AlexanderObregon/data-mapping-with-springs-modelattribute-annotation-b41704c2521a)
* [AttributeConverter vs Embeddable in JPA](https://www.wimdeblauwe.com/blog/2021/03/01/attributeconverter-vs-embeddable-in-jpa/?ref=footer)
* [handle one to many relationship in thymeleaf using spring mvc](https://stackoverflow.com/questions/50617760/handle-one-to-many-relationship-in-thymeleaf-using-spring-mvc)
* [JPA and Hibernate Many To Many Mapping without Joined Entity in Spring Boot](https://hellokoding.com/jpa-many-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/)
* [Equals and hashcode implementation considerations](https://www.wimdeblauwe.com/blog/2021/04/26/equals-and-hashcode-implementation-considerations/?ref=footer)
* [Accessing Request Parameters in Spring MVC Binding Without Setters](https://javanexus.com/blog/accessing-request-parameters-spring-mvc-binding-without-setters)
* [A Custom Data Binder in Spring MVC](https://www.baeldung.com/spring-mvc-custom-data-binder)
* [HTTP Request and Response Logging Using Logbook in Spring](https://www.baeldung.com/spring-logbook-http-logging)
* [Bootstrap Dynamic Modal with Spring Boot and Thymeleaf](https://blog.nazrulkabir.com/2018/04/dynamic-modal-with-spring-boot-and-thymeleaf/)
* [Keycloak Embedded in a Spring Boot Application](https://www.baeldung.com/keycloak-embedded-in-spring-boot-app)
* [Use Apache Causeway™ to rapidly develop domain-driven apps or modular monoliths in Java, on top of the Spring Boot platform](https://github.com/apache/causeway?tab=readme-ov-file)
