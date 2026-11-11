# Mana Suraksha — Phase 1: Core Java Foundation

**Planned date:** 12 November 2026  
**Estimated study time:** 3–4 hours  
**Status:** Planned — not yet implemented

## Goal

Start the Java 17 foundation for Mana Suraksha. Understand each concept, write the code, run tests, and be able to explain the design in a placement interview.

## Learning and implementation plan

| Time | Activity | Expected outcome |
|---|---|---|
| 30 min | Revise classes, objects, interfaces, and enums | Explain each concept with a small example |
| 45 min | Create a Java 17 Maven project | Project builds successfully |
| 45 min | Define `ThreatAnalyzer<T>` | A generic interface for different threat analyzers |
| 45 min | Implement `AnalysisResult` | A result model for score, risk level, reasons, recommendations, and limitations |
| 30 min | Implement `RiskLevel` | An enum for LOW, SUSPICIOUS, and HIGH |
| 30 min | Add and run basic tests | Validate result construction and risk-level behavior |
| 15 min | Review, document, commit, and push | A clean GitHub commit with a meaningful message |

## Initial package structure

```text
mana-suraksha/
└── src/
    ├── main/
    │   └── java/
    │       └── com/manasuraksha/
    │           ├── analyzer/
    │           │   └── ThreatAnalyzer.java
    │           └── model/
    │               ├── AnalysisResult.java
    │               └── RiskLevel.java
    └── test/
        └── java/
            └── com/manasuraksha/
```

## Definition of done

- [ ] Java 17 Maven project builds successfully.
- [ ] `ThreatAnalyzer<T>` is understood and implemented.
- [ ] `AnalysisResult` is implemented with validated data.
- [ ] `RiskLevel` enum is implemented.
- [ ] Basic tests pass.
- [ ] I can explain every class and important line without simply reading the code.
- [ ] Changes are committed and pushed to GitHub.

## Interview practice

Be ready to answer:
1. What is an interface in Java, and why use one here?
2. What is a generic type such as `ThreatAnalyzer<T>`?
3. What is an enum, and why is `RiskLevel` an enum?
4. Why should an analysis result keep its findings and recommendations together?
5. What is the difference between a planned feature and an implemented feature?

## Important project note

This document is a study plan only. The classes and tests listed above are targets for this session; they must not be described as completed until they have actually been implemented and tested.

**Suggested commit message after completing the work:** `Add Phase 1 Core Java foundation`
