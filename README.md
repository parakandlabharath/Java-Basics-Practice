# Java Basics Practice

A growing collection of Java programs and small exercises practiced while learning Java fundamentals and preparing for fresher-level Java interviews.

## Topics and examples

- **Basics:** Hello World, pre-decrement, nested loops, star patterns, prime numbers, and number programs.
- **OOP:** inheritance, abstract classes, interfaces, and Java 17 sealed classes.
- **Packages:** a user-defined package and importing a class from it.
- **Collections:** `TreeSet`, `Comparable`, `HashSet`, `equals()`, and `hashCode()`.
- **Mini projects:** Employee Management System.

## Folder guide

| Folder | Contents |
| --- | --- |
| `basics/` | Operators, loops, patterns, prime numbers, and package examples |
| `oops/` | Inheritance, abstract classes, interfaces, and sealed classes |
| `collections/` | Collection framework practice |
| `collections/comparable/` | Product sorting using `Comparable` and `TreeSet` |
| `collections/hashset/` | Product storage and updates using `HashSet` |
| `data/` | Sample text data used by practice programs |
| `mini-projects/` | Guided console application |
| `projects/` | Employee Management project source |
| `originals/` | Earlier source snapshots kept for reference; some are incomplete or need correction |

## Run a Java program

Install JDK 17 or later. Open a terminal in the folder containing the program and compile/run it:

```bash
javac HelloWorld.java
java HelloWorld
```

For the package example, compile from the repository root:

```bash
javac -d out basics/packages/com/java/basics/Sample.java basics/packages/PackageDemo.java
java -cp out PackageDemo
```

For the collection examples, compile the files in the same example folder:

```bash
cd collections/comparable
javac Product.java ProductComparableDemo.java
java ProductComparableDemo
```

```bash
cd ../hashset
javac Product.java ProductOperation.java ProductHashSetDemo.java
java ProductHashSetDemo
```

The two collection examples intentionally keep separate `Product` classes because they demonstrate different implementations. Compile each example from its own folder.

## Mini project

### Employee Management System

A Java console application to add, view, search, update, and delete employee records.

- [Source code](projects/employee-management/src/employeeapp)
- [Project guide and run instructions](projects/employee-management/README.md)
- Concepts: OOP, encapsulation, constructors, ArrayList, exception handling, and input validation.
- Current scope: in-memory storage; records reset when the application exits.

## Learning note

These are hands-on learning exercises, not claims of production-ready applications. The `originals/` folder preserves earlier snapshots; use the organized folders above for the cleaned-up examples.