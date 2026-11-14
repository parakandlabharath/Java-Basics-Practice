package employeeapp;

import java.io.IOException;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll() throws IOException;
    void saveAll(List<Employee> employees) throws IOException;
}
