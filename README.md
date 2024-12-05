### PROJECT DETAILS ###
Suppose that you are working in a company as a software developer. Your company is developing a Car
Rental Application. A web application and a mobile application will be developed. You are included in
the team that is responsible for developing server-side services.
Your team is responsible for developing all required Restful services which are required for web/mobile
applications.

**Base Technical Requirements and Rules**

1. You should create a Spring Boot web application. You should use JPA and Spring Data JPA. You are
not allowed to use pure JDBC or Spring JdbcTemplate. But you can use native SQL in the Data JPA
Repositories.

2. Generate an API documentation for your Restful services using OpenAPI (Swagger). Be careful, you
will not develop a web application, you will use Swagger and it will produce the required API
documentation.

3. You are not allowed to use Entity Objects for the input and output of Restful services. Your services
should only accept and return Data Transfer Objects. You can use projection methods or a mapping
library like mapstruct (https://mapstruct.org ) for such transformations.

4. Database selection is free of choice, but don’t use any database specific command in your project.
Switching to another database must be done easily by changing application.properties file and the
dependencies.

5. You must initialize your database automatically with at least one record in each table. You are free to
select any method you like for initialization.

6. Draw a UML class diagram for your model classes.

7. Develop seperate service classes for each entity. For example: ReservationService, CarService,
MemberService etc.

    - All service classes should contain base methods for related Entity. For example your MemberService should contain methods for creating, reading a member and returning all members
    - If you need, you can add extra methods for updating entity.
    - You should also add all other required methods according to the business requirements (see below)

9. Develop seperate controller classes according to the Resful Services requirements (listed below). For
example: ReservationController, CarController, CarServiceController, MemberController etc.

11. Use 3-layer architecture: Controller-Service-Repository. You can use Service beans in the Controller,
you can use Repository in the Service beans. But you can not use Repository in the RestController
classes.
12. Develop Junit tests for testing all of your service methods
13. Apply resource naming rules.
14. It is forbidden to use FetchType.EAGER
15. It is forbidden to use CascadeType.ALL and CascadeType.REMOVE. You can use the other cascade
types depending on your requirements.

UML Class Diagram Link:https://lucid.app/lucidchart/28f61c0b-c533-4fec-ae69-74905e969758/edit?viewport_loc=-1553%2C450%2C3797%2C1977%2CHWEp-vi-RSFO&invitationId=inv_c27c4465-a107-41b2-94c8-37c9c11dc933