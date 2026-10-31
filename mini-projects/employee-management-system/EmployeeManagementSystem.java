import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A small console-based employee management application.
 * Demonstrates classes, encapsulation, collections, loops and input validation.
 */
public class EmployeeManagementSystem {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Map<Integer, Employee> EMPLOYEES = new LinkedHashMap<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> searchEmployee();
                case 4 -> updateEmployee();
                case 5 -> deleteEmployee();
                case 0 -> {
                    running = false;
                    System.out.println("Thank you for using Employee Management System.");
                }
                default -> System.out.println("Invalid option. Please choose 0 to 5.");
            }
        }
        SCANNER.close();
    }

    private static void printMenu() {
        System.out.println("\n====== EMPLOYEE MANAGEMENT SYSTEM ======");
        System.out.println("1. Add employee");
        System.out.println("2. View all employees");
        System.out.println("3. Search employee by ID");
        System.out.println("4. Update employee");
        System.out.println("5. Delete employee");
        System.out.println("0. Exit");
    }

    private static void addEmployee() {
        int id = readInt("Employee ID: ");
        if (EMPLOYEES.containsKey(id)) {
            System.out.println("An employee with this ID already exists.");
            return;
        }

        String name = readText("Employee name: ");
        String department = readText("Department: ");
        double salary = readPositiveDouble("Monthly salary: ");

        EMPLOYEES.put(id, new Employee(id, name, department, salary));
        System.out.println("Employee added successfully.");
    }

    private static void viewEmployees() {
        if (EMPLOYEES.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }

        System.out.println("\nID | Name | Department | Monthly Salary");
        System.out.println("-----------------------------------------");
        for (Employee employee : EMPLOYEES.values()) {
            System.out.println(employee);
        }
    }

    private static void searchEmployee() {
        int id = readInt("Enter employee ID: ");
        Employee employee = EMPLOYEES.get(id);

        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println(employee);
        }
    }

    private static void updateEmployee() {
        int id = readInt("Enter employee ID to update: ");
        Employee employee = EMPLOYEES.get(id);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        String name = readText("New name: ");
        String department = readText("New department: ");
        double salary = readPositiveDouble("New monthly salary: ");

        employee.setName(name);
        employee.setDepartment(department);
        employee.setSalary(salary);
        System.out.println("Employee updated successfully.");
    }

    private static void deleteEmployee() {
        int id = readInt("Enter employee ID to delete: ");
        Employee removed = EMPLOYEES.remove(id);

        if (removed == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println("Employee deleted successfully.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = SCANNER.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readPositiveDouble(String message) {
        while (true) {
            System.out.print(message);
            String input = SCANNER.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (Double.isFinite(value) && value > 0) {
                    return value;
                }
                System.out.println("Enter an amount greater than zero.");
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = SCANNER.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static class Employee {
        private final int id;
        private String name;
        private String department;
        private double salary;

        Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        void setName(String name) {
            this.name = name;
        }

        void setDepartment(String department) {
            this.department = department;
        }

        void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return id + " | " + name + " | " + department + " | " + salary;
        }
    }
}
