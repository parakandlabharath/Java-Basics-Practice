# Employee Management System

A beginner-friendly Java console project for managing employee records. It is designed as a hands-on learning project for practising Core Java concepts.

## Features
- Add employees with unique IDs
- Display all employees
- Search employees by ID
- Update employee details
- Delete employee records
- Validate text and numeric input

## Technology and concepts
- Java 17
- Classes, objects, constructors, encapsulation
- ArrayList and List
- Loops, methods, and switch expressions
- Exception handling and input validation

## Project structure
```text
employee-management/
├── README.md
└── src/
    └── employeeapp/
        ├── Employee.java
        ├── EmployeeManager.java
        └── Main.java
```

## Run the project
Open a terminal in this folder and run:

**Windows PowerShell**
```powershell
javac -d out src/employeeapp/*.java
java -cp out employeeapp.Main
```

**macOS / Linux**
```bash
mkdir -p out
javac -d out src/employeeapp/*.java
java -cp out employeeapp.Main
```

Requires JDK 17 or later.

## Example
```text
=== Employee Management System ===
1. Add employee
2. View all employees
3. Search by ID
4. Update employee
5. Delete employee
0. Exit
Choose an option: 1
Employee ID: 101
Name: Asha
Department: Engineering
Salary: 35000
Employee added.
```

## Interview explanation
- **Encapsulation:** employee fields are private and accessed through methods.
- **Constructor:** initializes an employee and validates its starting values.
- **Collections:** an ArrayList stores employee objects and grows dynamically.
- **Separation of responsibilities:** Employee models one record, EmployeeManager performs operations, and Main handles console interaction.
- **Exception handling:** invalid input is reported and the menu continues.

## Current limitations
This first version stores records in memory, so data is cleared when the program exits. It does not yet use MySQL, JDBC, or a graphical interface.

## Planned improvements
1. Save records using file handling.
2. Add unit tests.
3. Add JDBC and MySQL persistence.
4. Add department filtering and salary sorting.

Build, run, and modify this project to understand each feature before discussing it in an interview.
