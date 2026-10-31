package employeeapp;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final EmployeeManager MANAGER = new EmployeeManager();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=== Employee Management System ===");

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> viewEmployees();
                    case 3 -> searchEmployee();
                    case 4 -> updateEmployee();
                    case 5 -> deleteEmployee();
                    case 0 -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Choose a valid option.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Input error: " + exception.getMessage());
            }
        }
        SCANNER.close();
    }

    private static void printMenu() {
        System.out.println("\n1. Add employee\n2. View all employees\n3. Search by ID");
        System.out.println("4. Update employee\n5. Delete employee\n0. Exit");
    }

    private static void addEmployee() {
        int id = readInt("Employee ID: ");
        String name = readText("Name: ");
        String department = readText("Department: ");
        double salary = readDouble("Salary: ");
        if (MANAGER.addEmployee(new Employee(id, name, department, salary))) {
            System.out.println("Employee added.");
        } else {
            System.out.println("That employee ID already exists.");
        }
    }

    private static void viewEmployees() {
        List<Employee> employees = MANAGER.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }
        employees.forEach(System.out::println);
    }

    private static void searchEmployee() {
        Employee employee = MANAGER.findById(readInt("Employee ID: "));
        System.out.println(employee == null ? "Employee not found." : employee);
    }

    private static void updateEmployee() {
        int id = readInt("Employee ID to update: ");
        String name = readText("New name: ");
        String department = readText("New department: ");
        double salary = readDouble("New salary: ");
        System.out.println(MANAGER.updateEmployee(id, name, department, salary)
                ? "Employee updated." : "Employee not found.");
    }

    private static void deleteEmployee() {
        System.out.println(MANAGER.deleteEmployee(readInt("Employee ID to delete: "))
                ? "Employee deleted." : "Employee not found.");
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        String value = SCANNER.nextLine().trim();
        if (value.isEmpty()) throw new IllegalArgumentException("Value cannot be empty.");
        return value;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(SCANNER.nextLine().trim());
                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }
                System.out.println("Enter a finite, non-negative amount.");
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
