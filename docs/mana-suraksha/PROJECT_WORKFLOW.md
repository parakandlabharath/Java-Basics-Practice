# Mana Suraksha — Project Workflow

> **Status:** Architecture and planning baseline; implementation has not started.
>
> **Purpose:** Help people, especially first-time smartphone users and rural communities, recognise suspicious messages, links and calls, report suspected fraud, and choose safer next steps.
>
> **Languages:** Telugu and English  
> **Clients:** Android application and web application  
> **Initial backend direction:** Java 17+ Core Java detection library, Spring Boot REST API, MySQL

## 1. Product vision

Mana Suraksha is a digital-safety assistant. A user should be able to share or enter a suspicious SMS, URL or phone number, receive an understandable risk assessment with reasons, and learn how to verify the claim safely. Users can also submit suspected-fraud reports and follow their status.

The application is an advisory tool. It must not claim that an item is genuine simply because no known threat was found. It must distinguish verified threats, community reports and unknown items.

## 2. People who use the system

| Actor | Main capabilities |
| --- | --- |
| Citizen | Check SMS/links/numbers, read safety guidance, submit and view own reports |
| Assisted-service volunteer | Help a citizen use the app with the citizen's consent; no unrestricted account access |
| Moderator | Review reports, request clarification, mark duplicates and record evidence-based decisions |
| Administrator | Manage roles, approved threat indicators, configuration and audit records |

## 3. First-release scope

### Included

1. **SMS checker**
   - User pastes text or shares selected text into the app.
   - Analyse urgency, impersonation, credential/OTP requests, payment pressure and suspicious contact details.
   - Return risk level, score, reasons, recommended safe actions and confidence/coverage notes.
   - Do not request broad access to a user's entire SMS inbox in the first release.

2. **URL checker**
   - Accept a URL pasted or shared by the user.
   - Parse and normalise the URL safely; inspect hostname, lookalike patterns, unusual subdomains, IP-literal hosts, punycode and known threat intelligence.
   - Do not open or execute the submitted destination automatically.
   - Treat redirects and external reputation checks as untrusted; never send private query strings or tokens to third parties.

3. **Call protection**
   - Let users manually check a phone number and view its reputation status.
   - Support community reports with moderation and anti-abuse controls.
   - Android call-screening integration is a later mobile milestone and requires user consent, supported Android APIs and role/permission eligibility.
   - Do not promise detection of every SIM call. Caller ID can be spoofed; a number's reputation is not proof of the caller's identity.

4. **Fraud reporting**
   - Submit category, description, approximate time, optional phone number/domain and optional redacted evidence.
   - Track report status and moderation history.
   - Clearly label community-reported information separately from independently verified threats.

5. **Bilingual safety guidance**
   - Telugu and English interface strings and explanations.
   - Simple language, large controls and clear actions.
   - Guidance for what to do after clicking a link, sharing an OTP or losing money.

### Not promised in the first release

- Guaranteed identification of all scams or genuine communications.
- Automatic rejection of every fraudulent call.
- Direct access to telecom operator/SIM signalling.
- Direct filing into government systems without an authorised integration.
- Automatic reading of all SMS or WhatsApp conversations.
- Guaranteed live threat intelligence if a provider is unavailable.

## 4. Main user workflows

### A. Check an SMS

1. Citizen opens Mana Suraksha.
2. Selects **Check a message**.
3. Pastes text or shares selected message text.
4. App displays a privacy notice and asks the user to remove OTPs, passwords, account numbers and other secrets.
5. Android/web client sends only the necessary text to the analysis API, or runs permitted local checks.
6. SMS analyzer extracts indicators and passes them to the risk engine.
7. Risk engine combines rule matches and available reputation evidence.
8. App displays one of: **High risk**, **Suspicious**, **No known threat**, or **Unable to check**.
9. App explains why and recommends safe next steps.
10. User may submit a separate fraud report.

### B. Check a link

1. User pastes or shares a URL.
2. Client warns not to open the link just to test it.
3. URL analyzer parses the URL without visiting it.
4. Local checks inspect hostname and URL structure.
5. Optional reputation provider is queried using the minimum necessary data.
6. Risk engine returns findings, source freshness and limitations.
7. App recommends using the organisation's independently obtained official app/site/contact details.

### C. Check a phone number / incoming call

1. User enters a number or the Android call-screening service receives a permitted call event.
2. Client normalises the number to a consistent format where possible.
3. Backend/local store checks approved threat indicators and moderated community reports.
4. Result distinguishes **verified threat**, **community reported**, **no known reports** and **unknown**.
5. Android may display a warning or apply a user-configured silence/reject action only where supported and authorised.
6. User can report the number; reports do not instantly make the number a verified threat.

### D. Submit and review a fraud report

1. User selects a report category.
2. User enters a short description and optional redacted evidence.
3. API validates input, applies rate limits and creates a report with status **SUBMITTED**.
4. Moderator checks duplicates, evidence and source reliability.
5. Moderator records a decision and reason.
6. Report moves to **UNDER_REVIEW**, **NEEDS_INFORMATION**, **VERIFIED**, **UNVERIFIED**, **DUPLICATE** or **CLOSED**.
7. User can view status and guidance. Only approved indicators are used as verified threat intelligence.

### E. Respond to a suspected compromise

The app displays an emergency guidance page: stop communicating with the suspected fraudster, do not share additional OTP/PIN/passwords, contact the bank/service provider using independently verified contact details, secure affected accounts, preserve evidence and use current official cybercrime reporting channels. The app must not collect banking credentials or OTPs.

## 5. Functional requirements

| ID | Requirement | Acceptance condition |
| --- | --- | --- |
| FR-01 | Select Telugu or English | All core navigation and warnings have both translations |
| FR-02 | Analyse SMS text | Returns risk category, reasons and next steps |
| FR-03 | Analyse URL | Parses safely and never auto-opens the URL |
| FR-04 | Check phone reputation | Shows source and verification status |
| FR-05 | Submit fraud report | Valid report receives a reference ID |
| FR-06 | Track own report | Authenticated user can view status and timeline |
| FR-07 | Moderate reports | Moderator action and reason are audited |
| FR-08 | Explain results | No result is shown as a bare unexplained score |
| FR-09 | Protect sensitive data | Secrets are not requested or retained by default |
| FR-10 | Work with weak connectivity | Basic local checks and saved guidance remain usable |

## 6. Non-functional requirements

- **Usability:** plain language, accessible contrast, large touch targets and low-complexity screens.
- **Privacy:** data minimisation, explicit consent, retention limits and deletion process.
- **Security:** TLS, secure authentication, server-side authorisation, input validation, rate limiting and audit logging.
- **Reliability:** graceful handling when database or reputation provider is unavailable.
- **Explainability:** show the indicators and sources behind a warning.
- **Accuracy:** measure false positives and false negatives on a labelled test set; thresholds must be calibrated rather than guessed.
- **Maintainability:** separate analyzers, risk policy, persistence and user interfaces.
- **Localisation:** translation resources, not hard-coded UI text.

## 7. Proposed repository layout

```text
mana-suraksha/
├── core-java/
│   ├── src/main/java/
│   │   └── org/manasuraksha/core/
│   │       ├── analysis/
│   │       ├── sms/
│   │       ├── url/
│   │       ├── phone/
│   │       ├── risk/
│   │       ├── report/
│   │       ├── model/
│   │       └── validation/
│   └── src/test/java/
├── backend/
│   └── src/main/java/org/manasuraksha/api/
│       ├── controller/
│       ├── service/
│       ├── repository/
│       ├── security/
│       ├── dto/
│       └── config/
├── android-app/
│   └── app/src/main/
├── web-app/
├── database/
│   ├── migrations/
│   └── seed/
├── docs/
│   ├── PROJECT_WORKFLOW.md
│   ├── SYSTEM_DESIGN.md
│   ├── CLASS_DIAGRAM.md
│   ├── API_DOCUMENTATION.md
│   ├── SECURITY_AND_PRIVACY.md
│   ├── FRAUD_DETECTION_LOGIC.md
│   └── TESTING_STRATEGY.md
├── .github/workflows/
├── README.md
├── CONTRIBUTING.md
└── LICENSE
```

## 8. Delivery milestones

| Milestone | Deliverable | Completion evidence |
| --- | --- | --- |
| M0 — Blueprint | Requirements, workflows, architecture and diagrams | Reviewed docs committed |
| M1 — Java foundation | Maven project, models, interfaces and enums | Builds on JDK 17+ |
| M2 — SMS engine | Rule-based SMS analysis | Unit tests and sample cases |
| M3 — URL engine | Safe URL parsing and checks | Malformed/hostile URL tests |
| M4 — Risk engine | Explainable result aggregation | Threshold and edge-case tests |
| M5 — Phone/report domain | Reputation states and report workflow | Validation and moderation tests |
| M6 — Persistence/API | MySQL schema and Spring Boot APIs | Integration tests |
| M7 — Web client | Citizen and moderator flows | Browser test evidence |
| M8 — Android client | Share-to-check and supported call-screening integration | Device/permission test evidence |
| M9 — Release readiness | Security, privacy, accessibility and documentation review | Release checklist and demo |

## 9. Git and contribution workflow

1. Create a feature branch for one focused task.
2. Implement the smallest complete change.
3. Add or update tests.
4. Run formatting, unit tests and build checks.
5. Review the diff for secrets, personal data and accidental scope changes.
6. Commit with a meaningful message, for example: `feat(core): add SMS indicator extraction`.
7. Push the branch and open a pull request.
8. Merge only after checks pass.

Suggested commit prefixes: `docs:`, `feat:`, `fix:`, `test:`, `refactor:`, `build:`.

## 10. Definition of done

A feature is complete only when its code, tests, documentation and user-facing explanation agree. Do not mark a feature as implemented because its design document exists. Do not publish real personal messages, phone numbers, OTPs, credentials or unredacted evidence in test fixtures or screenshots.

## 11. Interview explanation

**Short explanation:** "Mana Suraksha is a bilingual digital-safety platform. Its modular Java engine analyses suspicious SMS messages, URLs and phone-number reputation, then returns an explainable risk assessment. A Spring Boot backend exposes the analysis and reporting APIs, MySQL stores user reports and moderated threat indicators, and Android/web clients provide accessible interfaces. The design separates community reports from verified threat intelligence and prioritises privacy and false-positive prevention."

**Be transparent:** At the blueprint stage, these are planned components and workflows, not completed features. Explain only the modules that have actually been implemented and tested.
