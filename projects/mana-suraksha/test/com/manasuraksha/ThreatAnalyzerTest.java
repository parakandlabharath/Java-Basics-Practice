package com.manasuraksha;

import com.manasuraksha.model.RiskLevel;
import com.manasuraksha.repository.FileReportRepository;
import com.manasuraksha.service.ReportService;
import com.manasuraksha.service.ThreatAnalyzer;
import java.nio.file.Files;
import java.nio.file.Path;

public class ThreatAnalyzerTest {
    public static void main(String[] args) throws Exception {
        ThreatAnalyzer analyzer = new ThreatAnalyzer();
        check(analyzer.analyzeSms("Your bank account is blocked. Share OTP immediately at https://fake.test").riskLevel() == RiskLevel.HIGH, "high-risk SMS");
        check(analyzer.analyzeSms("Hello, meeting moved to 5 PM").riskLevel() == RiskLevel.LOW, "ordinary SMS");
        check(analyzer.analyzeLink("https://bit.ly/abc").riskLevel() == RiskLevel.MEDIUM, "shortened link");
        check(analyzer.analyzeLink("javascript:alert(1)").riskLevel() == RiskLevel.HIGH, "unsafe protocol");
        check(analyzer.analyzeCall("Caller asked me to share UPI PIN", true).riskLevel() == RiskLevel.HIGH, "credential request on call");
        check(analyzer.analyzeCall("Caller asked about office timings", true).riskLevel() == RiskLevel.LOW, "ordinary call");

        Path folder = Files.createTempDirectory("mana-suraksha-test");
        Path file = folder.resolve("reports.txt");
        ReportService service = new ReportService(analyzer, new FileReportRepository(file));
        service.analyzeSms("Share OTP immediately");
        check(service.getReports().size() == 1, "save and load report");
        Path backup = folder.resolve("backup").resolve("reports-backup.txt");
        new FileReportRepository(file).backupTo(backup);
        check(new FileReportRepository(backup).findAll().size() == 1, "backup and restore report");
        Files.deleteIfExists(file);
        Files.deleteIfExists(backup);
        Files.deleteIfExists(backup.getParent());
        Files.deleteIfExists(folder);
        expectInvalid(() -> analyzer.analyzeSms(" "));
        System.out.println("ThreatAnalyzerTest: all tests passed.");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError("Failed: " + label);
    }

    private static void expectInvalid(Runnable action) {
        try { action.run(); throw new AssertionError("Expected invalid input rejection."); }
        catch (IllegalArgumentException expected) { }
    }
}
