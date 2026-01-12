# Expense Tracker

A Spring Boot application for tracking personal expenses with user management and address support.

## Features

- User management (CRUD operations)
- User address management (One-to-Many relationship)
- Expense tracking (to be implemented)
- Category management (to be implemented)
- Profile-based database configuration:
  - **Local**: H2 in-memory database with JDBC
  - **Staging**: PostgreSQL with JPA (ready for implementation)

## Prerequisites

- Java 21
- Maven 3.6+
- (Optional) PostgreSQL for staging profile

## Getting Started

### Running the Application

1. **Clone the repository** (if applicable)

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   Or with a specific profile:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
   ```

### Default Configuration

- **Port**: 8080
- **Profile**: local (H2 in-memory database)
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (empty)

## API Endpoints

### User Endpoints

- `POST /user` - Create a new user
- `GET /user` - Get all users
- `GET /user/{id}` - Get user by ID
- `PUT /user` - Update user
- `DELETE /user/{id}` - Delete user

### Address Endpoints

- `POST /address` - Create a new address
- `GET /address` - Get all addresses

## Example API Calls

### Create User
```bash
curl -X POST http://localhost:8080/user \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  }'
```

### Get All Users
```bash
curl http://localhost:8080/user
```

### Create Address
```bash
curl -X POST http://localhost:8080/address \
  -H "Content-Type: application/json" \
  -d '{
    "addressLine1": "123 Main Street",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001",
    "country": "USA",
    "user": {
      "id": 100
    }
  }'
```

## Database Schema

The application uses the following tables:
- `EXPT_USER` - User information
- `EXPT_USER_ADDRESS` - User addresses (One-to-Many with User)
- `EXPT_CATEGORY` - Expense categories
- `EXPT_EXPENSE` - Expenses (One-to-Many with User, Many-to-One with Category)

Schema is automatically created from `src/main/resources/schema.sql` when using the local profile.

## Profiles

### Local Profile (Default)
- Uses H2 in-memory database
- Uses JDBC for data access
- H2 console enabled at `/h2-console`
- SQL scripts executed automatically

### Staging Profile
- Uses PostgreSQL database
- Uses JPA for data access
- Configure database connection in `application-staging.properties`

To switch profiles:
```bash
# Via environment variable
export SPRING_PROFILES_ACTIVE=staging

# Via command line
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=staging"
```

## Running Tests

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=UserControllerTest
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/app/expensetracker/
│   │       ├── constroller/     # REST controllers
│   │       ├── service/          # Business logic
│   │       ├── repository/       # Data access layer
│   │       │   ├── jdbc/         # JDBC implementation (local)
│   │       │   └── jpa/          # JPA implementation (staging)
│   │       ├── dao/              # Entity classes
│   │       └── config/           # Configuration classes
│   └── resources/
│       ├── application.properties
│       ├── application-local.properties
│       ├── application-staging.properties
│       └── schema.sql
└── test/
    └── java/                     # Test classes
```

## Technologies Used

- Spring Boot 3.3.6
- Spring Data JDBC (local profile)
- Spring Data JPA (staging profile)
- H2 Database (local)
- PostgreSQL (staging)
- Lombok
- JUnit 5

## License

This is a personal project for learning Spring Boot features.
