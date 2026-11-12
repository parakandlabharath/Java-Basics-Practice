# 01 — System Design Fundamentals

## What is System Design?

System Design is the process of deciding how software components work together to satisfy requirements. It covers the application structure, data flow, APIs, storage, security, performance and reliability.

A useful design explains both **what components exist** and **why they are arranged that way**.

## 1. Requirements

### Functional requirements
Features the system must provide. Example for a Student Management System:

- Admin can register and manage students.
- Faculty can enter or update marks for assigned courses.
- Students can view their own profile and results.
- Accounts staff can manage fee records.

### Non-functional requirements
Quality expectations and constraints:

- Security: users can access only permitted data.
- Performance: common requests should respond promptly.
- Availability: the system should remain usable despite some failures.
- Scalability: capacity can grow as users and data increase.
- Maintainability: code and modules can be changed safely.

## 2. Client-server architecture

The client is the interface used by a person or another application. The server receives requests, applies rules and returns responses.

```text
User
  |
  v
Web browser / Android app
  |
  | HTTPS request
  v
Backend REST API
  |
  v
Business logic
  |
  v
Database
  |
  v
HTTP response -> Client
```

The client should not connect directly to a private application database. The backend enforces authentication, authorization and business rules.

## 3. API and HTTP basics

An API is a defined way for software components to communicate. A REST-style API commonly uses HTTP methods:

| Method | Typical purpose | Example |
| --- | --- | --- |
| GET | Read a resource | `GET /api/students/42` |
| POST | Create a resource | `POST /api/students` |
| PUT | Replace/update a resource | `PUT /api/students/42` |
| PATCH | Partially update | `PATCH /api/students/42` |
| DELETE | Delete a resource | `DELETE /api/students/42` |

Common response codes:

- `200 OK`: request succeeded.
- `201 Created`: a resource was created.
- `400 Bad Request`: invalid input.
- `401 Unauthorized`: authentication is missing or invalid.
- `403 Forbidden`: authenticated user lacks permission.
- `404 Not Found`: resource does not exist.
- `500 Internal Server Error`: unexpected server failure.

## 4. Layered backend architecture

A common Spring Boot arrangement is:

```text
HTTP Request
    |
    v
Controller  -> validates request shape and maps HTTP
    |
    v
Service     -> applies business rules
    |
    v
Repository  -> performs data access
    |
    v
Database
```

Example: `StudentController` receives a request, `StudentService` checks whether the student can be registered, and `StudentRepository` persists the record.

Keep responsibilities separated. A controller should not contain all database and business logic.

## 5. Authentication and authorization

- **Authentication:** verifies who the user is (for example, login).
- **Authorization:** decides what that user is allowed to do (for example, a faculty member can update marks only for assigned courses).

Use role-based access control (RBAC) as one possible approach. Roles such as ADMIN, FACULTY, STUDENT and ACCOUNTS can be mapped to permissions. In a real application, also check ownership and assignment rules; a role alone may be too broad.

## 6. Database design basics

Identify entities, attributes and relationships before creating tables.

Example entities:

- Student
- Branch
- Course
- Faculty
- Enrollment
- Assessment/Marks
- Attendance
- Fee

Use primary keys to identify rows and foreign keys to maintain relationships. Normalize data to reduce duplication, then add indexes for frequently queried columns after considering write cost and actual query patterns.

## 7. Validation and error handling

Validate untrusted input at the API boundary and enforce important constraints in the service and database. Return clear client errors for invalid requests, avoid leaking secrets or stack traces, and log enough diagnostic context for maintainers.

## Interview checkpoint

**Question:** Why use Controller → Service → Repository instead of putting everything in one class?

**Answer:** It separates HTTP handling, business rules and persistence. That makes each responsibility easier to test, understand and change. It is a useful pattern, although small applications may use simpler structures.
