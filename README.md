# Java Basics Practice

A collection of Java programs practiced while learning Java fundamentals and preparing for interviews.

## Topics
- Number programs
- Control flow and enums
- Object-oriented programming and inheritance
- Collections
- Employee examples
- Practice data

## Folder guide
- `basics/` — number programs, operators, loops, switch expressions, enums
- `oops/` — inheritance, abstract classes, interfaces, and shape examples
- `collections/` — ArrayList and employee examples
- `data/` — sample text data used for practice
- `originals/` — source files kept as uploaded, including unfinished experiments

## Run a Java program
Install a JDK, open a terminal in the folder containing the `.java` file, then run:

```bash
javac FileName.java
java ClassName
```

The public class name must match the Java filename. Some files in `originals/` are unfinished or contain compilation errors; they are preserved for reference rather than presented as verified runnable solutions.

## Mini projects

### Employee Management System
A Java 17 console application to add, view, search, update, and delete employee records.

- **Source code:** [projects/employee-management/src/employeeapp](projects/employee-management/src/employeeapp)
- **Project guide and run instructions:** [Employee Management System README](projects/employee-management/README.md)
- **Concepts:** OOP, encapsulation, constructors, ArrayList, exception handling, and input validation.
- **Current scope:** In-memory storage; records reset when the application exits.

