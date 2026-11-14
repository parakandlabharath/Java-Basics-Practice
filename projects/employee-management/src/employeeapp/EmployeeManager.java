package employeeapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private final List<Employee> employees = new ArrayList<>();
    private final EmployeeRepository repository;

    /** In-memory constructor retained for small exercises and tests. */
    public EmployeeManager() {
        this.repository = null;
    }

    /** Loads existing records from the supplied repository. */
    public EmployeeManager(EmployeeRepository repository) throws IOException {
        if (repository == null) throw new IllegalArgumentException("Employee repository is required.");
        this.repository = repository;
        employees.addAll(repository.findAll());
    }

    public boolean addEmployee(Employee employee) throws IOException {
        if (employee == null) throw new IllegalArgumentException("Employee is required.");
        if (findById(employee.getId()) != null) return false;
        employees.add(employee);
        try {
            persist();
            return true;
        } catch (IOException exception) {
            employees.remove(employee);
            throw exception;
        }
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public Employee findById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) return employee;
        }
        return null;
    }

    public boolean updateEmployee(int id, String name, String department, double salary) throws IOException {
        int index = indexOf(id);
        if (index < 0) return false;

        Employee replacement = new Employee(id, name, department, salary);
        Employee previous = employees.set(index, replacement);
        try {
            persist();
            return true;
        } catch (IOException exception) {
            employees.set(index, previous);
            throw exception;
        }
    }

    public boolean deleteEmployee(int id) throws IOException {
        int index = indexOf(id);
        if (index < 0) return false;

        Employee removed = employees.remove(index);
        try {
            persist();
            return true;
        } catch (IOException exception) {
            employees.add(index, removed);
            throw exception;
        }
    }

    private int indexOf(int id) {
        for (int index = 0; index < employees.size(); index++) {
            if (employees.get(index).getId() == id) return index;
        }
        return -1;
    }

    private void persist() throws IOException {
        if (repository != null) repository.saveAll(getAllEmployees());
    }
}
