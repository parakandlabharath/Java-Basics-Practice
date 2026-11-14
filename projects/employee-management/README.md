# Employee Management System

A small Java 17 console application for managing employee records. This is the primary Employee Management mini-project in this repository.

## Features

- Add employees with unique positive IDs
- List all employees
- Search by employee ID
- Update employee details
- Delete employee records
- Validate names, departments, IDs, and salary values
- Save records to a local file and reload them after restarting the application
- Write data through a temporary file and replace the previous file after a successful write

## Concepts demonstrated

- Classes, objects, constructors, and encapsulation
- ArrayList and collection operations
- Interface-based repository design
- File I/O, UTF-8, Base64 encoding, and atomic file replacement
- Exception handling and input validation
- Unit-style tests using only the Java standard library

## Project structure

```text
employee-management/
├── .gitignore
├── README.md
├── src/employeeapp/
│   ├── Employee.java
│   ├── EmployeeManager.java
│   ├── EmployeeRepository.java
│   ├── CsvEmployeeRepository.java
│   └── Main.java
└── test/employeeapp/
    └── EmployeeManagerTest.java
```

## Run the application

Requires JDK 17 or later. Open a terminal in this folder.

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

The application creates `data/employees.txt` automatically. That local data file is ignored by Git so personal employee records are not committed. Back it up separately if the records matter.

## Run tests

**Windows PowerShell**

```powershell
javac -d out src/employeeapp/*.java test/employeeapp/EmployeeManagerTest.java
java -cp out employeeapp.EmployeeManagerTest
```

**macOS / Linux**

```bash
mkdir -p out
javac -d out src/employeeapp/*.java test/employeeapp/EmployeeManagerTest.java
java -cp out employeeapp.EmployeeManagerTest
```

The tests cover adding, duplicate-ID rejection, searching, updating, deleting, persistence after reload, and invalid employee values. GitHub Actions compiles the Java examples and runs these tests on pushes and pull requests.

## Architecture

- **Employee** — stores one employee and validates its fields.
- **EmployeeRepository** — defines the persistence contract.
- **CsvEmployeeRepository** — reads/writes a simple local text data file.
- **EmployeeManager** — applies add/search/update/delete rules and saves successful changes.
- **Main** — handles the console menu and user input.

The repository uses Base64-encoded text fields so employee names or departments containing commas, pipes, or line breaks do not corrupt the record format.

## Known limitations

- This is a single-user local console project, not a deployed HR system.
- The file is not encrypted. Do not store sensitive real employee data in it.
- It does not yet use JDBC, MySQL, authentication, or a graphical interface.
- The older single-file demo remains under `mini-projects/employee-management-system/` for comparison; use this structured project as the main version.

## Interview explanation

The application separates the model, business operations, persistence contract, file repository, and console UI. The manager depends on an `EmployeeRepository` interface rather than directly depending on file operations. The file implementation loads records when the manager starts and saves after successful CRUD operations. Tests use a temporary file so they can verify that records survive a new manager instance without touching a user's real data.
