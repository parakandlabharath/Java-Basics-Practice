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

A Java console application to add, view, search, update, and delete employee records, with local file persistence and JDK-only tests.

- [Source code](projects/employee-management/src/employeeapp)
- [Project guide and run instructions](projects/employee-management/README.md)
- Concepts: OOP, encapsulation, constructors, ArrayList, exception handling, and input validation.
- Current scope: local file persistence using a repository interface; records are reloaded after restart.
- [Run and test guide](projects/employee-management/README.md) — setup, file format, tests, architecture, and limitations.

## Learning note

These are hands-on learning exercises, not claims of production-ready applications. The `originals/` folder preserves earlier snapshots; use the organized folders above for the cleaned-up examples.

## Repaired practice examples

Some uploaded drafts had incomplete class declarations, incorrect imports, missing methods, or class/file-name mismatches. Clean runnable versions are organized here:

- `basics/CharacterIncrementDemo.java` — corrected public class name and post-increment explanation.
- `basics/ArmstrongNumber.java` — supports Armstrong numbers with any number of digits and validates input.
- `basics/AmicableNos.java` — validates positive inputs and calculates proper-divisor sums.
- `basics/SwitchEnumDemo.java` — corrected enum and switch syntax.
- `oops/InheritanceDemo.java` — corrected Animal/Dog inheritance and overriding.
- `oops/FinalClassDemo.java` — demonstrates inherited methods and accessing parent state through a getter.
- `oops/InterfaceMultipleInheritanceDemo.java` — demonstrates implementing multiple interfaces.
- `collections/CollectionsSortDemo.java` — corrected `Collections.sort()` usage.

The `originals/` directory is a historical archive of the uploaded drafts. It intentionally preserves incomplete or broken snapshots for learning/reference; use the repaired examples above instead. Files in that archive ending in `.txt` are not part of the runnable Java source set.

## Project completion tracker

See [Project Completion Tracker](docs/PROJECT_COMPLETION_TRACKER.md) for the current status of all active repositories, the ten-point completion gate, and the next steps. A project is not marked finished just because CI builds it.

## System Design and Git learning notes

I also document the engineering concepts I practise while preparing for Java backend interviews:

- [System Design learning path](docs/system-design/README.md) — fundamentals, client-server architecture, APIs, layered backend design, database design, scalability and reliability.
- [Student Management System case study](docs/system-design/02-student-management-system.md) — roles, relational data model, API outline and faculty marks-update workflow.
- [Git and GitHub fundamentals](docs/git/GIT_GITHUB_FUNDAMENTALS.md) — repositories, staging, commits, push/pull, branching, merge conflicts, pull requests and CI/CD.

These are learning notes and design exercises. They describe concepts and proposed architecture, not a claim that the example system is already implemented or deployed.

## Automatic compilation check

A GitHub Actions workflow compiles the organized Java examples with JDK 17 and runs the Employee Management tests whenever code is pushed or a pull request targets `main`. The historical `originals/` archive is excluded intentionally.
