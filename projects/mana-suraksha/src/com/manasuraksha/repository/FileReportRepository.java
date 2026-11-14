package com.manasuraksha.repository;

import com.manasuraksha.model.RiskLevel;
import com.manasuraksha.model.ThreatCategory;
import com.manasuraksha.model.ThreatReport;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class FileReportRepository implements ReportRepository {
    private final Path file;
    private final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    private final Base64.Decoder decoder = Base64.getUrlDecoder();

    public FileReportRepository(Path file) {
        if (file == null) throw new IllegalArgumentException("Report file path is required.");
        this.file = file;
    }

    @Override
    public void save(ThreatReport report) throws IOException {
        if (report == null) throw new IllegalArgumentException("Report is required.");
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) Files.createDirectories(parent);
        String line = String.join("|", encode(report.id()), report.createdAt().toString(),
                report.category().name(), report.riskLevel().name(), encode(report.reason())) + System.lineSeparator();
        Files.writeString(file, line, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Override
    public List<ThreatReport> findAll() throws IOException {
        List<ThreatReport> reports = new ArrayList<>();
        if (!Files.exists(file)) return reports;
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        for (int index = 0; index < lines.size(); index++) {
            String[] fields = lines.get(index).split("\\|", -1);
            if (fields.length != 5) throw new IOException("Invalid report record at line " + (index + 1) + ".");
            try {
                reports.add(new ThreatReport(decode(fields[0]), Instant.parse(fields[1]),
                        ThreatCategory.valueOf(fields[2]), RiskLevel.valueOf(fields[3]), decode(fields[4])));
            } catch (RuntimeException exception) {
                throw new IOException("Invalid report record at line " + (index + 1) + ".", exception);
            }
        }
        return reports;
    }

    @Override
    public void backupTo(Path destination) throws IOException {
        if (destination == null) throw new IllegalArgumentException("Backup destination is required.");
        if (!Files.exists(file)) throw new IOException("No report data exists yet.");
        Path parent = destination.toAbsolutePath().getParent();
        if (parent != null) Files.createDirectories(parent);
        Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    private String encode(String value) {
        return encoder.encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
    private String decode(String value) {
        return new String(decoder.decode(value), StandardCharsets.UTF_8);
    }
}
