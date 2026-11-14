package employeeapp;

import java.io.IOException;
import java.util.List;
import java.nio.file.Path;

public interface EmployeeRepository {
    List<Employee> findAll() throws IOException;
    void saveAll(List<Employee> employees) throws IOException;
    void backupTo(Path destination) throws IOException;
}
