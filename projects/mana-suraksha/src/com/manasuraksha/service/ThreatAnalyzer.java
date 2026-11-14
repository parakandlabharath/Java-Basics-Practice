package com.manasuraksha.service;

import com.manasuraksha.model.AnalysisResult;
import com.manasuraksha.model.RiskLevel;
import com.manasuraksha.model.ThreatCategory;
import java.net.URI;
import java.util.Locale;
import java.util.Set;

public class ThreatAnalyzer {
    private static final Set<String> URGENCY = Set.of("urgent", "immediately", "blocked", "suspended", "expire", "விரைவாக");
    private static final Set<String> CREDENTIALS = Set.of("otp", "pin", "password", "upi", "cvv", "account number", "bank details");
    private static final Set<String> REWARDS = Set.of("prize", "won", "lottery", "cashback", "free gift");
    private static final Set<String> SHORTENERS = Set.of("bit.ly", "tinyurl.com", "t.co", "is.gd", "cutt.ly", "rb.gy");

    public AnalysisResult analyzeSms(String message) {
        String text = requireText(message, "SMS message").toLowerCase(Locale.ROOT);
        boolean asksSecrets = containsAny(text, CREDENTIALS);
        boolean urgency = containsAny(text, URGENCY);
        boolean reward = containsAny(text, REWARDS);
        boolean link = text.contains("http://") || text.contains("https://") || text.contains("www.");

        if (asksSecrets && (urgency || link)) {
            return new AnalysisResult(ThreatCategory.SMS, RiskLevel.HIGH,
                    "Message combines a request for sensitive credentials with urgency or a link. Never share OTP, PIN, CVV, or passwords.");
        }
        if (asksSecrets || (reward && (urgency || link))) {
            return new AnalysisResult(ThreatCategory.SMS, RiskLevel.HIGH,
                    "Message contains common credential-theft or fake-reward warning signs. Verify through the official organisation's app or phone number.");
        }
        if (urgency || link || reward) {
            return new AnalysisResult(ThreatCategory.SMS, RiskLevel.MEDIUM,
                    "Message contains a pressure, link, or reward pattern. Do not open unexpected links; verify independently.");
        }
        return new AnalysisResult(ThreatCategory.SMS, RiskLevel.LOW,
                "No configured warning pattern was found. This is not proof that the message is genuine.");
    }

    public AnalysisResult analyzeLink(String value) {
        String input = requireText(value, "Link");
        try {
            URI uri = URI.create(input.contains("://") ? input : "https://" + input);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            if (!("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) || host == null || host.isBlank()) {
                return new AnalysisResult(ThreatCategory.LINK, RiskLevel.HIGH,
                        "The address is malformed or uses an unsupported protocol. Do not open it.");
            }
            String normalizedHost = host.toLowerCase(Locale.ROOT);
            if (uri.getUserInfo() != null || input.contains("@")) {
                return new AnalysisResult(ThreatCategory.LINK, RiskLevel.HIGH,
                        "The address contains user-info syntax that can disguise the real destination.");
            }
            if (normalizedHost.matches("\\d{1,3}(\\.\\d{1,3}){3}") || normalizedHost.startsWith("xn--")) {
                return new AnalysisResult(ThreatCategory.LINK, RiskLevel.HIGH,
                        "The address uses a raw IP address or an internationalized host that needs careful verification.");
            }
            if (SHORTENERS.contains(normalizedHost)) {
                return new AnalysisResult(ThreatCategory.LINK, RiskLevel.MEDIUM,
                        "This is a shortened link, so its final destination is hidden. Verify it before opening.");
            }
            if (normalizedHost.contains("secure-login") || normalizedHost.contains("account-verify") ||
                    normalizedHost.contains("claim-prize")) {
                return new AnalysisResult(ThreatCategory.LINK, RiskLevel.HIGH,
                        "The host contains a common impersonation or prize-claim pattern. Navigate to the official site yourself.");
            }
            return new AnalysisResult(ThreatCategory.LINK, RiskLevel.LOW,
                    "No configured link warning pattern was found. The destination has not been visited or verified.");
        } catch (IllegalArgumentException exception) {
            return new AnalysisResult(ThreatCategory.LINK, RiskLevel.HIGH,
                    "The address could not be parsed safely. Do not open it.");
        }
    }

    public AnalysisResult analyzeCall(String notes, boolean callerIsKnown) {
        String text = requireText(notes, "Call notes").toLowerCase(Locale.ROOT);
        boolean asksSecrets = containsAny(text, CREDENTIALS) || text.contains("remote access") || text.contains("screen share");
        boolean urgency = containsAny(text, URGENCY) || text.contains("transfer money");
        if (asksSecrets || (urgency && !callerIsKnown)) {
            return new AnalysisResult(ThreatCategory.CALL, RiskLevel.HIGH,
                    "Caller behavior matches a high-risk social-engineering pattern. End the call and contact the organisation using an official number.");
        }
        if (!callerIsKnown || urgency) {
            return new AnalysisResult(ThreatCategory.CALL, RiskLevel.MEDIUM,
                    "Caller identity or request needs independent verification. Do not act under pressure.");
        }
        return new AnalysisResult(ThreatCategory.CALL, RiskLevel.LOW,
                "No configured warning pattern was found. Caller identity is not independently verified.");
    }

    private static boolean containsAny(String text, Set<String> terms) {
        return terms.stream().anyMatch(text::contains);
    }

    private static String requireText(String value, String label) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(label + " cannot be empty.");
        if (value.length() > 5000) throw new IllegalArgumentException(label + " is too long (maximum 5000 characters).");
        return value.trim();
    }
}
