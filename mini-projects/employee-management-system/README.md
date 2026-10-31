# Employee Management System

A small, original Java console application for practising the fundamentals used in entry-level Java interviews.

## Features

- Add an employee with a unique ID.
- Display all employees.
- Search for an employee by ID.
- Update an employee's name, department and salary.
- Delete an employee.
- Validate numeric input and reject empty text fields.
- Keep records in insertion order while the program is running.

## Requirements

- JDK 17 or later
- Terminal, VS Code, Eclipse or another Java IDE

## Run the application

Open this folder in a terminal and run:

```bash
javac EmployeeManagementSystem.java
java EmployeeManagementSystem
```

## Example session

```text
====== EMPLOYEE MANAGEMENT SYSTEM ======
1. Add employee
2. View all employees
3. Search employee by ID
4. Update employee
5. Delete employee
0. Exit
Choose an option: 1
Employee ID: 101
Employee name: Ananya
Department: Engineering
Monthly salary: 35000
Employee added successfully.
```

## Concepts demonstrated

| Concept | Where it is used |
|---|---|
| Class and object | Employee records |
| Encapsulation | Employee fields are private |
| Constructor | Initialises each employee |
| Collections | LinkedHashMap stores records by ID |
| CRUD operations | Add, view, search, update and delete |
| Exception handling | Handles invalid numeric input |
| Loops and switch | Runs the menu until the user exits |
| Method decomposition | Each menu operation has its own method |

## Current scope and limitations

This is a learning version. Data is stored in memory, so records disappear when the application exits. It does not yet use a database, authentication, or a graphical interface. These are possible future improvements, not completed features.

## Interview explanation

"I built a menu-driven Employee Management System in Java. It supports adding, viewing, searching, updating and deleting employee records. I used a LinkedHashMap so each employee can be found using a unique ID while keeping insertion order. I separated each operation into methods and added input validation to handle invalid entries. The current version stores data in memory, which keeps the project focused on Core Java fundamentals."

## Practice questions

1. Why did you choose a Map instead of an ArrayList?
2. What is the purpose of the Employee constructor?
3. How does the application prevent duplicate employee IDs?
4. What happens when the user enters letters instead of a number?
5. Why are the employee fields private?
6. What would you change to store records after the program closes?

## Planned improvements

- Split Employee into its own Java file.
- Add file-based persistence.
- Add automated tests.
- Add JDBC and MySQL persistence.
