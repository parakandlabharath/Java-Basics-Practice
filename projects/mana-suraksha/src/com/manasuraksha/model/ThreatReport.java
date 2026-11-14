package com.manasuraksha.model;

import java.time.Instant;
import java.util.UUID;

public record ThreatReport(String id, Instant createdAt, ThreatCategory category, RiskLevel riskLevel, String reason) {
    public ThreatReport {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Report ID is required.");
        if (createdAt == null || category == null || riskLevel == null) throw new IllegalArgumentException("Report fields are required.");
        if (reason == null || reason.isBlank()) throw new IllegalArgumentException("Report reason is required.");
    }

    public static ThreatReport from(AnalysisResult result) {
        return new ThreatReport(UUID.randomUUID().toString(), Instant.now(), result.category(), result.riskLevel(), result.reason());
    }
}
