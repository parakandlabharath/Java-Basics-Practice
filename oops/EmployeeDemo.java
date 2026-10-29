abstract class Employee {
    int empId;
    String empName;

    public Employee(int empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    public void display() {
        System.out.println("Employee Id: " + empId);
        System.out.println("Employee Name: " + empName);
    }

    public abstract void calculateSalary();
}

class developer extends Employee {
    int salary;
    public developer(int empId, String empName, int salary) {
        super(empId, empName);
        this.salary = salary;
    }
    public void calculateSalary() {
        System.out.println("Developer Salary: " + salary);
    }
    public double calculateBonus() {
        return salary * 0.1;
    }
}

class tester extends Employee {
    int salary;
    public tester(int empId, String empName, int salary) {
        super(empId, empName);
        this.salary = salary;
    }
    public void calculateSalary() {
        System.out.println("Tester Salary: " + salary);
    }
    public double calculateBonus() {
        return salary + 7000;
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        developer d = new developer(101, "Bharath", 50000);
        d.display();
        d.calculateSalary();
        System.out.println("Developer Bonus: " + d.calculateBonus());

        tester t = new tester(102, "Miakasa", 40000);
        t.display();
        t.calculateSalary();
        System.out.println("Tester Bonus: " + t.calculateBonus());
    }
}