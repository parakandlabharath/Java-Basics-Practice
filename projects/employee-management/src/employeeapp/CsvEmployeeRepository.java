package employeeapp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Stores employee records in a small local text file.
 * Text fields are Base64 encoded so commas, pipes and line breaks are safe.
 */
public class CsvEmployeeRepository implements EmployeeRepository {
    private final Path file;
    private final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    private final Base64.Decoder decoder = Base64.getUrlDecoder();

    public CsvEmployeeRepository(Path file) {
        if (file == null) throw new IllegalArgumentException("File path is required.");
        this.file = file;
    }

    @Override
    public List<Employee> findAll() throws IOException {
        List<Employee> employees = new ArrayList<>();
        if (!Files.exists(file)) return employees;

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        for (int index = 0; index < lines.size(); index++) {
            String line = lines.get(index);
            if (line.isBlank()) continue;
            String[] fields = line.split("\\|", -1);
            if (fields.length != 4) {
                throw new IOException("Invalid employee record at line " + (index + 1) + ".");
            }
            try {
                int id = Integer.parseInt(fields[0]);
                String name = new String(decoder.decode(fields[1]), StandardCharsets.UTF_8);
                String department = new String(decoder.decode(fields[2]), StandardCharsets.UTF_8);
                double salary = Double.parseDouble(fields[3]);
                employees.add(new Employee(id, name, department, salary));
            } catch (IllegalArgumentException exception) {
                throw new IOException("Invalid employee record at line " + (index + 1) + ".", exception);
            }
        }
        return employees;
    }

    @Override
    public void saveAll(List<Employee> employees) throws IOException {
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) Files.createDirectories(parent);

        StringBuilder content = new StringBuilder();
        for (Employee employee : employees) {
            content.append(employee.getId()).append('|')
                    .append(encoder.encodeToString(employee.getName().getBytes(StandardCharsets.UTF_8))).append('|')
                    .append(encoder.encodeToString(employee.getDepartment().getBytes(StandardCharsets.UTF_8))).append('|')
                    .append(employee.getSalary()).append(System.lineSeparator());
        }

        Path temporaryFile = file.resolveSibling(file.getFileName() + ".tmp");
        Files.writeString(temporaryFile, content, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        try {
            Files.move(temporaryFile, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException exception) {
            Files.move(temporaryFile, file, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
