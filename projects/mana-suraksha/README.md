# Mana Suraksha — Core Java Digital Safety Assistant

A beginner-friendly, bilingual (English/Telugu) Core Java prototype for reviewing suspicious SMS messages, links, and call behaviour. It produces a simple risk warning and stores a report locally.

> **Prototype, not a guaranteed scam detector.** It uses transparent keyword and URL-pattern rules. It does not contact banks, telecom providers, government systems, or external websites. A LOW result does not prove a message, caller, or link is safe.

## Current features

- Analyze SMS text for credential requests, urgency, unexpected links, and fake-reward patterns.
- Inspect a URL without opening it or contacting its destination.
- Review call notes for requests involving OTPs, PINs, passwords, remote access, or urgent money transfers.
- Show LOW / MEDIUM / HIGH risk with an explanation.
- Save report metadata locally without saving the original SMS or phone number.
- View saved reports and create timestamped backup copies.
- Choose English or Telugu menu prompts.

## Technology and concepts

- Java 17
- OOP, enums, records, interfaces, encapsulation
- Rule-based text analysis and safe URI parsing
- File I/O, Base64 encoding, repository pattern
- Exception handling and input validation
- JDK-only tests

## Run locally

Requires JDK 17 or later. Open a terminal in this folder.

**Windows PowerShell**

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object FullName)
java -cp out com.manasuraksha.Main
```

**macOS / Linux**

```bash
mkdir -p out
javac -d out $(find src -name '*.java')
java -cp out com.manasuraksha.Main
```

## Run tests

**Windows PowerShell**

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object FullName)
javac -cp out -d out (Get-ChildItem -Recurse test -Filter *.java | ForEach-Object FullName)
java -cp out com.manasuraksha.ThreatAnalyzerTest
```

**macOS / Linux**

```bash
mkdir -p out
javac -d out $(find src -name '*.java')
javac -cp out -d out $(find test -name '*.java')
java -cp out com.manasuraksha.ThreatAnalyzerTest
```

## Data and privacy

- Reports are saved in `data/reports.txt`; backup copies are created in `backups/`.
- Both folders are excluded from Git. Copy backup files to a separate device or trusted cloud storage.
- The app stores the category, risk level, timestamp, and reason; it does not store the full SMS text or phone number.
- Report files are Base64-encoded for safe text storage, **not encrypted**. Do not store sensitive content in report reasons.

## Architecture

```text
Main (console + language selection)
  └── ReportService
        ├── ThreatAnalyzer (SMS / URL / call rules)
        └── ReportRepository (interface)
              └── FileReportRepository (local file + backup)
```

## Known limitations and next phase

- No Android app, browser extension, SMS inbox permission, call-log permission, or background monitoring is included.
- The current link analyzer parses the URL only; it never follows redirects or checks domain reputation.
- Rule-based checks can produce false positives and false negatives. Verify concerns through official channels.
- No cloud sync or database is configured. A later Spring Boot API + database and Android client can reuse the analyzer rules after security/privacy review.

## Interview explanation

The console collects user-provided text and sends it to a rule-based `ThreatAnalyzer`. Each analyzer returns an `AnalysisResult` containing a category, risk level, and reason. `ReportService` converts it into a `ThreatReport` and stores it through the `ReportRepository` interface. The file repository encodes text fields, reloads reports, and supports backup export. The code deliberately avoids opening suspicious links or claiming that heuristic results are definitive.
