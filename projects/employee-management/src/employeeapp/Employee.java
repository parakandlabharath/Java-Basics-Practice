package employeeapp;

public class Employee {
    private final int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        setName(name);
        setDepartment(department);
        setSalary(salary);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name.trim();
    }

    public void setDepartment(String department) {
        if (department == null || department.isBlank()) throw new IllegalArgumentException("Department cannot be empty.");
        this.department = department.trim();
    }

    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative.");
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Department: %s | Salary: %.2f", id, name, department, salary);
    }
}
