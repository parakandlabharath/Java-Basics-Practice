# System Design Learning Notes

A beginner-friendly record of the System Design concepts practised while preparing for Java backend and full-stack interviews.

> Learning status: foundational notes and conceptual examples. The Student Management System below is a design exercise, not a claim that a production application has been implemented.

## Learning path

1. [System Design fundamentals](01-system-design-fundamentals.md) — requirements, client-server, APIs and layered architecture.
2. [Student Management System case study](02-student-management-system.md) — roles, modules, data model and request flow.
3. [Scalability and reliability](03-scalability-and-reliability.md) — load balancing, caching, replication, backups, sharding and failover.

## A simple interview method

For a design question, work through these steps:

1. Clarify the problem and identify users.
2. List functional requirements (what the system must do).
3. List non-functional requirements (performance, availability, security, consistency).
4. Estimate scale when the question requires it.
5. Draw the high-level components and request flow.
6. Design the data model and API contracts.
7. Discuss failure cases, security and trade-offs.
8. Explain what you would monitor and improve next.

## Key distinction

- **High-level design (HLD):** major components, services, databases and how they communicate.
- **Low-level design (LLD):** classes, interfaces, methods, object relationships and detailed behaviour.

## How this connects to Java

A typical Spring Boot backend can use:

- Controller: receives HTTP requests and returns responses.
- Service: applies business rules.
- Repository: reads and writes persistent data.
- Entity/model: represents application data.
- DTO: defines the request or response shape.

These are common architectural choices, not mandatory rules for every application.
