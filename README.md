# GitHub Lookup

A Spring Boot REST API for fetching GitHub repository data and storing it locally in PostgreSQL. Built as a practice project to explore backend development concepts including OpenFeign clients, JPA repositories, and RESTful API design.

## 🚀 Features

- Fetch repositories from any GitHub user via GitHub API
- Import non-fork repositories with branch information into local database
- Full CRUD operations on stored repositories
- RESTful endpoints for managing repository data
- Error handling with custom exceptions
- API documentation with Swagger/OpenAPI

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Cloud OpenFeign** - Declarative REST client
- **Spring Data JPA** - Data persistence
- **PostgreSQL** - Database
- **Lombok** - Boilerplate reduction
- **Bean Validation** - Request validation
- **SpringDoc OpenAPI** - API documentation

## 📋 Prerequisites

- JDK 17 or higher
- Maven 3.6+
- Docker & Docker Compose
- GitHub Personal Access Token

## ⚙️ Setup

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd GitHubLookup
```

### 2. Generate GitHub Token

1. Go to GitHub → Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Generate new token with `repo` scope
3. Copy the token

### 3. Configure application.properties

Add your GitHub token to `src/main/resources/application.properties`:

```properties
github.token=your_github_token_here
spring.datasource.url=jdbc:postgresql://localhost:54321/mydatabase
spring.datasource.username=myuser
spring.datasource.password=secret
```

### 4. Start PostgreSQL with Docker

```bash
docker-compose up -d
```

This will start:
- PostgreSQL on port `54321`
- pgAdmin on port `5050` (accessible at http://localhost:5050)

### 5. Create Database Table

Run the SQL in `schema.sql`:

```sql
CREATE TABLE IF NOT EXISTS repo (
    id BIGSERIAL PRIMARY KEY,
    owner VARCHAR NOT NULL,
    name VARCHAR NOT NULL
);
```

### 6. Run the application

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## 📚 API Endpoints

### Import Repositories

```http
GET /users/database/import/{userName}
```

Fetches all non-fork repositories from a GitHub user and imports them into the database with branch information.

### Get User Repositories from Database

```http
GET /users/database/{userName}
```

Retrieves all repositories stored for a specific user.

### Get All Repositories

```http
GET /users/database
```

Retrieves all repositories from the database.

### Create Repository

```http
POST /users
Content-Type: application/json

{
  "username": "octocat",
  "repositoryName": "Hello-World"
}
```

**Required fields:**
- `username` - GitHub username (not null, not empty)
- `repositoryName` - Repository name (not null, not empty)

### Update Repository (Partial)

```http
PATCH /users/database/{id}
Content-Type: application/json

{
  "newOwner": "octocat",
  "newRepoName": "Updated-Repo-Name"
}
```

**Optional fields:** Both fields are optional for partial updates.

### Replace Repository

```http
PUT /users/database/{id}
Content-Type: application/json

{
  "owner": "octocat",
  "name": "Hello-World"
}
```

### Delete Repository

```http
DELETE /users/database/{id}
```

## 📖 API Documentation

Once the application is running, visit:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 🗂️ Project Structure

```
src/main/java/com/githublookup/
├── client/          # Feign clients for external APIs
├── controller/      # REST controllers
├── dto/             # Data Transfer Objects
├── error/           # Exception handlers
├── model/           # JPA entities
├── repository/      # Data repositories
└── service/         # Business logic
```

## 🔍 Key Components

- **GitHubClient**: Feign client for GitHub API integration
- **DataRepository**: JPA repository with custom queries
- **GitHubErrorHandler**: Global exception handling for API errors
- **RepoErrorHandler**: Exception handling for database operations

## 🐛 Error Handling

The API returns standardized error responses:

- `404 Not Found` - User or resource not found
- `400 Bad Request` - Invalid request body
- `406 Not Acceptable` - Unsupported Accept header

## 🎯 Learning Goals

This project demonstrates:
- RESTful API design principles
- Integration with external APIs using OpenFeign
- Spring Data JPA for database operations
- Exception handling and error responses
- DTO pattern for clean architecture
- Docker for local development environment

## 📝 License

This is a practice project for educational purposes.

## 🤝 Contributing

This is a personal learning project, but suggestions and feedback are welcome!
