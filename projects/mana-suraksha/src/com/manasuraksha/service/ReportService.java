package com.manasuraksha.service;

import com.manasuraksha.model.AnalysisResult;
import com.manasuraksha.model.ThreatReport;
import com.manasuraksha.repository.ReportRepository;
import java.io.IOException;
import java.util.List;

public class ReportService {
    private final ThreatAnalyzer analyzer;
    private final ReportRepository repository;

    public ReportService(ThreatAnalyzer analyzer, ReportRepository repository) {
        if (analyzer == null || repository == null) throw new IllegalArgumentException("Analyzer and repository are required.");
        this.analyzer = analyzer;
        this.repository = repository;
    }

    public ThreatReport analyzeSms(String message) throws IOException {
        return save(analyzer.analyzeSms(message));
    }
    public ThreatReport analyzeLink(String link) throws IOException {
        return save(analyzer.analyzeLink(link));
    }
    public ThreatReport analyzeCall(String notes, boolean knownCaller) throws IOException {
        return save(analyzer.analyzeCall(notes, knownCaller));
    }
    public List<ThreatReport> getReports() throws IOException {
        return repository.findAll();
    }
    private ThreatReport save(AnalysisResult result) throws IOException {
        ThreatReport report = ThreatReport.from(result);
        repository.save(report);
        return report;
    }
}
