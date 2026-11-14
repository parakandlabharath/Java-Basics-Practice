package employeeapp;

import java.nio.file.Files;
import java.nio.file.Path;

/** Lightweight tests using only the JDK; run with the repository's GitHub Actions workflow. */
public class EmployeeManagerTest {
    public static void main(String[] args) throws Exception {
        Path folder = Files.createTempDirectory("employee-manager-test");
        Path dataFile = folder.resolve("employees.txt");
        EmployeeRepository repository = new CsvEmployeeRepository(dataFile);

        EmployeeManager manager = new EmployeeManager(repository);
        check(manager.addEmployee(new Employee(101, "Asha, Kumar", "R&D | Platform", 35000)), "add employee");
        check(!manager.addEmployee(new Employee(101, "Duplicate", "IT", 40000)), "reject duplicate ID");
        check(manager.findById(101) != null, "find employee");
        check(manager.updateEmployee(101, "Asha Kumar", "Engineering", 42000), "update employee");

        Path backupFile = folder.resolve("backup").resolve("employees-backup.txt");
        repository.backupTo(backupFile);
        EmployeeRepository backupRepository = new CsvEmployeeRepository(backupFile);
        check(backupRepository.findAll().size() == 1, "export backup file");
        check("Asha Kumar".equals(backupRepository.findAll().get(0).getName()), "restore backup contents");

        EmployeeManager reloaded = new EmployeeManager(repository);
        check(reloaded.findById(101) != null, "load persisted employee");
        check("Asha Kumar".equals(reloaded.findById(101).getName()), "persist updated name");
        check(reloaded.findById(101).getSalary() == 42000, "persist updated salary");
        check(reloaded.deleteEmployee(101), "delete employee");

        EmployeeManager afterDelete = new EmployeeManager(repository);
        check(afterDelete.findById(101) == null, "persist deletion");

        expectIllegalArgument(() -> new Employee(0, "Name", "IT", 1000), "invalid ID");
        expectIllegalArgument(() -> new Employee(102, " ", "IT", 1000), "blank name");
        expectIllegalArgument(() -> new Employee(102, "Name", "IT", -1), "negative salary");

        Files.deleteIfExists(dataFile);
        Files.deleteIfExists(backupFile);
        Files.deleteIfExists(backupFile.getParent());
        Files.deleteIfExists(folder);
        System.out.println("EmployeeManagerTest: all tests passed.");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError("Failed: " + label);
    }

    private static void expectIllegalArgument(Runnable action, String label) {
        try {
            action.run();
            throw new AssertionError("Expected IllegalArgumentException: " + label);
        } catch (IllegalArgumentException expected) {
            // Expected validation failure.
        }
    }
}
