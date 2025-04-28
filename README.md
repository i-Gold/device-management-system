# Device Management System

## Overview
A Spring Boot application for managing customers, machines, and devices with a relational data model. It provides a REST API for CRUD operations, entity associations, and license management, using an H2 database with Flyway migrations. The API can be secured with HTTP Basic authentication when the `secured` profile is active.

## Features
- **Entities**:
    - Customer, Machine, Device (each with `id` and `name`).
    - Relationships:
        - Customer has many Machines and Devices.
        - Machine can be associated with a Device.
        - Machine can have a UUID license.
- **REST API**:
    - CRUD operations for all entities.
    - Endpoints to associate Machines/Devices with Customers.
    - Endpoint to remove Machine licenses (`/machines/removeAllLicenses`).
- **Security**: HTTP Basic authentication (username: `admin`, password: `admin123`) in `secured` profile.
- **Database**: H2 file-based database with Flyway-managed schema.
- **Testing**: Unit tests for services and integration tests for API endpoints.

## Prerequisites
- Java 17+
- Maven 3.6+
- Git

## Setup
1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd device-management-system
   ```

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
    - Default (no security):
      ```bash
      mvn spring-boot:run
      ```
    - Secured (requires authentication):
      ```bash
      mvn spring-boot:run -Dspring-boot.run.profiles=secured
      ```

4. **Access H2 Console** (optional):
    - URL: `http://localhost:8081/h2-console`
    - JDBC URL: `jdbc:h2:mem:device_management_system`
    - Username: `root`
    - Password: (empty)

## Testing
Run tests:
```bash
mvn test
```