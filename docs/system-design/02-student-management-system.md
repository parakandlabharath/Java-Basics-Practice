# 02 — Student Management System: Design Case Study

This is a practice design based on the requirements discussed during learning. It is a conceptual architecture, not a completed or deployed application.

## Problem statement

Build a system where an institution can manage student details, branches, courses, attendance, marks, results and fee records.

## Users and permissions

| Role | Example responsibilities |
| --- | --- |
| Admin | Create student records, manage branches/courses and assign faculty |
| Faculty | View assigned classes and enter/update marks or attendance |
| Student | View own profile, attendance and published results |
| Accounts | Record and review fee payments |

Authorization must check both role and relevant record ownership/assignment.

## Proposed architecture

```text
Web browser / Android client
          |
        HTTPS
          |
          v
Spring Boot REST API
          |
   Authentication + RBAC
          |
   Controller layer
          |
    Service layer
          |
   Repository layer
          |
        MySQL
```

### Example backend modules

- `auth`: login, identity and access rules.
- `students`: student profiles and registration.
- `academics`: branches, courses and enrollments.
- `faculty`: faculty profiles and course assignments.
- `attendance`: attendance records.
- `assessment`: marks and result calculations.
- `fees`: fee records and payment status.

These are suggested logical modules; they do not imply that the code currently exists.

## Initial relational data model

| Table | Important columns | Relationship |
| --- | --- | --- |
| `students` | student_id (PK), register_no, name, mobile, branch_id (FK) | Many students belong to one branch |
| `branches` | branch_id (PK), branch_name | One branch has many students/courses |
| `courses` | course_id (PK), course_code, title, branch_id (FK) | A course belongs to a branch |
| `faculty` | faculty_id (PK), name, email | Faculty can teach multiple courses |
| `course_faculty` | course_id (FK), faculty_id (FK) | Join table for course/faculty assignment |
| `enrollments` | enrollment_id (PK), student_id (FK), course_id (FK), academic_year | Connects students and courses |
| `attendance` | attendance_id (PK), enrollment_id (FK), date, status | Attendance for an enrollment |
| `marks` | mark_id (PK), enrollment_id (FK), assessment, score | Assessment results for an enrollment |
| `fees` | fee_id (PK), student_id (FK), amount, due_date, status | Fee entries for a student |

Design notes:

- Use unique constraints for values such as register number and course code.
- Use foreign keys to prevent orphan records.
- Consider a unique constraint on `(student_id, course_id, academic_year)` in enrollments.
- Store money in an exact decimal type, not floating point.
- Decide how result publication and mark corrections are audited.
- Apply indexes to real lookup patterns, such as register number and foreign-key columns.

## Example workflow: faculty updates marks

1. Faculty signs in.
2. Client sends an authenticated request, for example `PATCH /api/marks/501`.
3. Backend authenticates the user.
4. Authorization checks that the faculty member is assigned to the relevant course.
5. Controller validates the request fields and score range.
6. Service applies business rules (for example, maximum score and whether marks are locked).
7. Repository updates the mark record in a database transaction.
8. API returns a success response; failures return an appropriate error without exposing internal details.

## Example API outline

| Endpoint | Purpose |
| --- | --- |
| `POST /api/students` | Register a student (admin) |
| `GET /api/students/{id}` | Read a student record (permission checked) |
| `GET /api/students/{id}/results` | View published results |
| `POST /api/enrollments` | Enroll a student in a course |
| `PATCH /api/marks/{id}` | Update marks (assigned faculty only) |
| `GET /api/students/{id}/attendance` | View attendance (permission checked) |

These are illustrative API contracts. Exact payloads, pagination, validation rules and status codes would be defined during implementation.

## Interview explanation (short version)

"I would use a client-server architecture. A web or Android client calls a Spring Boot REST API over HTTPS. The backend separates HTTP handling, business logic and database access using Controller, Service and Repository layers. MySQL stores students, branches, courses, enrollments, attendance, marks and fees with primary and foreign keys. Authentication identifies the user, while role and assignment checks control actions such as faculty mark updates."

## What remains before this becomes a real application

- Implement the Spring Boot modules and API contracts.
- Add database migrations and constraints.
- Implement secure authentication and authorization.
- Add unit and integration tests.
- Define audit, backup, monitoring and deployment procedures.
