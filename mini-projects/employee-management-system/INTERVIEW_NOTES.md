# Interview Notes — Employee Management System

Use these notes to understand and explain the project in your own words. Do not memorise an answer without being able to point to the relevant code.

## 60-second explanation

This is a menu-driven Java console application that manages employee records. A user can add, display, search, update and delete employees. I used a LinkedHashMap with employee ID as the key, so searching by ID is straightforward and duplicate IDs can be checked before insertion. I used separate methods for each operation and input validation to handle incorrect entries. The current version stores data only while the program is running.

## Design choices

- **LinkedHashMap:** maps a unique employee ID to an Employee object and preserves insertion order.
- **Private fields:** protects employee data from direct access.
- **Constructor:** initialises an Employee object with its starting values.
- **Small methods:** keeps menu handling separate from each operation.
- **Input validation:** prevents invalid numeric input from crashing the menu loop.

## Questions to practise

### 1. Why use LinkedHashMap?
A Map stores key-value pairs. Here the employee ID is the key and the Employee object is the value. LinkedHashMap also preserves insertion order when displaying records.

### 2. How do you avoid duplicate IDs?
Before adding a record, the program calls containsKey(id). If the ID already exists, it stops the add operation.

### 3. What is CRUD?
CRUD means Create, Read, Update and Delete. These operations are represented by add, view/search, update and delete in this application.

### 4. What is encapsulation?
Encapsulation keeps data and related behaviour together and controls access to fields. The Employee fields are private and are changed through methods.

### 5. Why catch NumberFormatException?
Integer.parseInt and Double.parseDouble throw this exception when the entered text is not a valid number. Catching it lets the user try again instead of terminating the program.

### 6. What is a limitation of this version?
The Map exists only in memory. When the application closes, its records are lost. File handling or a database can be added for persistence.

## Be ready to demonstrate

1. Add two employees with different IDs.
2. Try adding the same ID twice.
3. Display all employees.
4. Search for an existing and a missing ID.
5. Update an employee.
6. Try entering letters for an ID or salary.
7. Delete an employee and display the list again.

## Honest project status

This is the first learning version. It uses in-memory storage and has not yet been extended with a database, GUI, login system or automated test suite. Only describe features that you have personally run and understood.
