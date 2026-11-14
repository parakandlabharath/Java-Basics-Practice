package com.manasuraksha.model;

public record AnalysisResult(ThreatCategory category, RiskLevel riskLevel, String reason) {
    public AnalysisResult {
        if (category == null || riskLevel == null) throw new IllegalArgumentException("Analysis fields are required.");
        if (reason == null || reason.isBlank()) throw new IllegalArgumentException("A reason is required.");
    }
}
