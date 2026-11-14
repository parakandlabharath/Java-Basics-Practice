package com.manasuraksha.repository;

import com.manasuraksha.model.ThreatReport;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ReportRepository {
    void save(ThreatReport report) throws IOException;
    List<ThreatReport> findAll() throws IOException;
    void backupTo(Path destination) throws IOException;
}
