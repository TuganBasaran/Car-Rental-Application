# Car Rental Application

A Spring Boot-based RESTful service application for a car rental management system. This project provides a complete backend for car rental operations including management of cars, members, reservations, equipment, and additional services.

## Overview

This car rental application provides functionalities for:
- Car inventory and status management
- Member registration and management
- Reservation creation and lifecycle management
- Location management
- Equipment and additional services management

## Technologies Used

- **Spring Boot 3.3.5**: Application framework
- **Spring Data JPA**: Data persistence
- **H2 Database**: In-memory database (configurable to other databases)
- **OpenAPI/Swagger**: API documentation
- **JUnit**: Testing framework
- **Gradle**: Build automation

## Project Structure

The application follows a 3-layer architecture:

```
src/
├── main/
│   ├── java/
│   │   └── cs_393_TZS/car_rental_application/
│   │       ├── CarRentalApplication.java
│   │       ├── config/
│   │       ├── controller/
│   │       ├── DTO/
│   │       ├── exception/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       ├── application.properties
│       └── data.sql
└── test/
    └── java/
        └── cs_393_TZS/car_rental_application/
            ├── CarRentalApplicationTests.java
            └── service/
```

## Key Components

### Model Layer
- **Car**: Represents rental vehicles with properties like barcode, license plate, model, type and status
- **Member**: Represents system users who can rent cars
- **Reservation**: Manages the rental process from creation to completion
- **Equipment**: Additional equipment that can be added to reservations
- **Services**: Additional services that can be offered with reservations
- **Location**: Pickup and drop-off locations

### Repository Layer
- JPA repositories for all entities with custom query methods

### Service Layer
- Implements business logic and validation
- Handles DTO conversions
- Key services include:
  - CarService
  - MemberService
  - ReservationService
  - EquipmentService
  - ServicesService
  - LocationService

### Controller Layer
- REST endpoints for client applications
- Implements proper HTTP status codes and error handling

## API Endpoints

The application provides the following main API endpoints:

### Car Management
- `GET /cars/{status}`: Get cars by status (AVAILABLE, RESERVED, LOANED, etc.)
- `GET /cars/available`: Get available cars by type and transmission
- `POST /cars`: Add a new car
- `DELETE /cars/{barcode}`: Delete a car by barcode

### Member Management
- `GET /members`: Get all members
- `GET /members/{id}`: Get member by ID
- `POST /members`: Add a new member
- `DELETE /members/{id}`: Delete a member

### Reservation Management
- `POST /reservations`: Create a new reservation
- `GET /reservations/status/{status}`: Get reservations by status
- `PUT /reservations/{reservationNumber}/loaned`: Mark car as loaned
- `PUT /reservations/{reservationNumber}/returned`: Mark car as returned
- `PUT /reservations/{reservationNumber}/cancelled`: Cancel reservation
- `PUT /reservations/{reservationNumber}/services`: Add service to reservation
- `PUT /reservations/{reservationNumber}/equipments`: Add equipment to reservation
- `DELETE /reservations/{reservationNumber}`: Delete a reservation

### Location Management
- `GET /locations`: Get all locations
- `GET /locations/{code}`: Get location by code
- `POST /locations`: Add a new location
- `DELETE /locations/{code}`: Delete a location

## Setup Instructions

1. **Prerequisites**:
   - Java 21
   - Gradle

2. **Clone the repository**:
   ```
   git clone https://github.com/username/Car-Rental-Application.git
   cd Car-Rental-Application
   ```

3. **Build the project**:
   ```
   ./gradlew build
   ```

4. **Run the application**:
   ```
   ./gradlew bootRun
   ```

5. **Access the API documentation**:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

## Database Configuration

The application uses H2 in-memory database by default. You can change the database by modifying the application.properties file:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

## Testing

The application includes JUnit tests for all service classes. Run tests with:

```
./gradlew test
```

## Business Rules

- Cars have statuses: AVAILABLE, RESERVED, LOANED, LOST, BEING_SERVICED
- Reservations have statuses: ACTIVE, PENDING, CONFIRMED, COMPLETED, CANCELLED, NONE
- Only available cars can be reserved
- When a car is reserved, its status changes to RESERVED
- When a car is picked up, its status changes to LOANED
- When a car is returned, its status changes to AVAILABLE
- Reservations can be cancelled if they are not already completed

## UML Class Diagram

The UML class diagram can be accessed at: [UML Class Diagram](https://lucid.app/lucidchart/28f61c0b-c533-4fec-ae69-74905e969758/edit?viewport_loc=-1553%2C450%2C3797%2C1977%2CHWEp-vi-RSFO&invitationId=inv_c27c4465-a107-41b2-94c8-37c9c11dc933)

## Contributors

- Team TZS

## License

This project is licensed under the MIT License.

TuganBasaran: Projeye uygun bir şekilde bir readme dosyası oluştur

Readme formatında olsun 

Workspace: Collecting workspace information# Car Rental Application

## Project Overview
This project is a server-side RESTful API service for a Car Rental System. It provides the backend functionality for web and mobile applications, allowing users to manage car reservations, member information, car inventory, and additional services.

## Features
- Car management (add, remove, search by status, type, etc.)
- Member registration and management
- Reservation system with status tracking (pending, active, completed, cancelled)
- Location management for pick-up and drop-off points
- Additional services and equipment options for rentals
- Comprehensive pricing system

## Technology Stack
- Java 21
- Spring Boot 3.3.5
- Spring Data JPA
- H2 Database (in-memory)
- JUnit 5 for testing
- Swagger/OpenAPI for API documentation

## Project Structure
The application follows a 3-layer architecture:
- **Controller Layer**: REST endpoints for client communication
- **Service Layer**: Business logic implementation
- **Repository Layer**: Data access and persistence

### Key Components
- **Models**: Car, Member, Reservation, Location, Equipment, Services
- **DTOs**: Data Transfer Objects for API requests/responses
- **Services**: Business logic implementation for each entity
- **Controllers**: RESTful API endpoints
- **Repositories**: Data access interfaces

## Setup Instructions

### Prerequisites
- JDK 21 or higher
- Gradle 8.10.2 or higher

### Running the Application
1. Clone the repository
2. Navigate to the project root directory
3. Run the application using Gradle:
   ```bash
   ./gradlew bootRun
   ```
4. The application will start on port 8080
5. Access the Swagger UI at: http://localhost:8080/swagger-ui/index.html

## API Documentation
The API is documented using OpenAPI (Swagger). After starting the application, you can access the documentation at:
```
http://localhost:8080/swagger-ui/index.html
```

### API Endpoints

#### Car Management
- `GET /cars/{status}` - Get cars by status
- `GET /cars/available` - Get available cars by type and transmission
- `POST /cars` - Add new car
- `DELETE /cars/{barcode}` - Delete car

#### Member Management
- `GET /members` - Get all members
- `GET /members/{id}` - Get member by ID
- `POST /members` - Add new member
- `DELETE /members/{id}` - Delete member

#### Reservation Management
- `POST /reservations` - Create new reservation
- `GET /reservations/status/{status}` - Get reservations by status
- `GET /reservations/{id}` - Get reservation by ID
- `PUT /reservations/{reservationNumber}/loaned` - Mark car as loaned
- `PUT /reservations/{reservationNumber}/returned` - Mark car as returned
- `PUT /reservations/{reservationNumber}/cancelled` - Cancel reservation
- `DELETE /reservations/{reservationNumber}` - Delete reservation

#### Location Management
- `GET /locations` - Get all locations
- `GET /locations/{code}` - Get location by code
- `POST /locations` - Add new location
- `DELETE /locations/{code}` - Delete location

## Testing
The project includes comprehensive JUnit tests for service classes. To run the tests:
```bash
./gradlew test
```

## Database
The application uses an in-memory H2 database which is initialized with sample data on startup. You can access the H2 console at:
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: 
```

## UML Class Diagram
The UML class diagram for the project can be found at:
[UML Class Diagram](https://lucid.app/lucidchart/28f61c0b-c533-4fec-ae69-74905e969758/edit?viewport_loc=-1553%2C450%2C3797%2C1977%2CHWEp-vi-RSFO&invitationId=inv_c27c4465-a107-41b2-94c8-37c9c11dc933)