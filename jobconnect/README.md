# JobConnect - Job Portal Backend

A production-style REST API where **recruiters** post jobs and **candidates** search, apply and track applications.
Built with Java 17, Spring Boot 3, Spring Security (JWT), Spring Data JPA/Hibernate and MySQL.

## Features
- Stateless **JWT authentication** (register / login)
- **Role-based authorization**: `ADMIN`, `RECRUITER`, `CANDIDATE`
- Layered architecture: Controller -> Service -> Repository, with DTOs (entities are never exposed)
- **Global exception handling** with a consistent JSON error format + request validation
- **Pagination, sorting and filtered search** (keyword, location, skill, experience) using JPA Specifications
- Apply to jobs (duplicate applications blocked), recruiters manage application status
- **Swagger UI** for API docs, **JUnit + Mockito** unit tests, MockMvc + H2 integration tests, **Docker** support

## Tech Stack
Java 17 | Spring Boot 3.3 | Spring Security | JWT (jjwt) | Spring Data JPA | Hibernate | MySQL | Swagger (springdoc) | JUnit 5 | Mockito | Maven | Docker

## Roles and permissions
| Action | Candidate | Recruiter | Admin |
|---|:-:|:-:|:-:|
| Register / login | yes | yes | (seeded) |
| Search / view jobs | yes | yes | yes |
| Post job | - | yes | - |
| Edit / delete job | - | own jobs | any |
| Apply to job | yes | - | - |
| View own applications | yes | - | - |
| View / update applications of a job | - | own jobs | any |
| List all users | - | - | yes |

## Run locally

### Option 1 - Docker (easiest)
```bash
docker compose up --build
```
API: http://localhost:8080  |  Swagger UI: http://localhost:8080/swagger-ui.html

### Option 2 - Maven + local MySQL
1. Install JDK 17, Maven and MySQL.
2. Set your MySQL password if it is not `root`:
   ```bash
   export DB_PASSWORD=your_password
   ```
3. Run:
   ```bash
   mvn spring-boot:run
   ```

A default admin is created on first start: `admin@jobconnect.com` / `Admin@123` (change via `ADMIN_EMAIL`, `ADMIN_PASSWORD`).
Set a strong `JWT_SECRET` (Base64, at least 32 bytes) in production.

## Run tests
```bash
mvn test
```

## API overview
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register as CANDIDATE or RECRUITER |
| POST | `/api/auth/login` | Login, returns JWT |
| POST | `/api/jobs` | Post a job (RECRUITER) |
| GET | `/api/jobs` | Search jobs: `keyword, location, skill, experience, page, size, sortBy, direction` |
| GET | `/api/jobs/{id}` | Job details |
| PUT | `/api/jobs/{id}` | Update job (owner / ADMIN) |
| DELETE | `/api/jobs/{id}` | Delete job (owner / ADMIN) |
| POST | `/api/jobs/{id}/apply` | Apply (CANDIDATE) |
| GET | `/api/applications/me` | My applications (CANDIDATE) |
| GET | `/api/jobs/{id}/applications` | Applications for a job (owner / ADMIN) |
| PATCH | `/api/applications/{id}/status` | APPLIED / SHORTLISTED / REJECTED / HIRED |
| GET | `/api/admin/users` | List users (ADMIN) |

## Quick demo with curl
```bash
# 1. Register a recruiter and copy the token from the response
curl -X POST localhost:8080/api/auth/register -H "Content-Type: application/json" \
  -d '{"name":"Rina","email":"rina@corp.com","password":"secret123","role":"RECRUITER"}'

# 2. Post a job
curl -X POST localhost:8080/api/jobs -H "Authorization: Bearer <TOKEN>" -H "Content-Type: application/json" \
  -d '{"title":"Java Backend Developer","description":"Build REST APIs","location":"Pune","skills":"Java,Spring Boot,MySQL","minExperience":0,"salary":600000}'

# 3. Search (any logged-in user)
curl "localhost:8080/api/jobs?skill=java&location=pune&sortBy=salary&direction=desc&page=0&size=5" \
  -H "Authorization: Bearer <TOKEN>"
```

## Project structure
```
src/main/java/com/ahad/jobconnect
  config/       SecurityConfig, OpenApiConfig, DataSeeder
  controller/   Auth, Job, Application, Admin controllers
  dto/          request/response records
  entity/       User, Job, JobApplication, enums
  exception/    custom exceptions + GlobalExceptionHandler
  repository/   Spring Data repositories + JobSpecifications
  security/     JwtService, JwtAuthFilter, CustomUserDetailsService
  service/      business logic
  util/         entity -> DTO mapper
```

## Author
Ahad Mulani - github.com/AhadMulani05
