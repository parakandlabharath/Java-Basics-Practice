# Project Completion Tracker

This page is the single overview for the unfinished projects in the current GitHub portfolio. Status is based on the source currently committed to the default branches and the latest GitHub Actions runs checked on 14 November 2026.

## Active repositories

| Project | Repository | Current state | Main unfinished work |
|---|---|---|---|
| Employee Management System | [Java-Basics-Practice](https://github.com/parakandlabharath/Java-Basics-Practice/tree/main/projects/employee-management) | Core Java mini-project with file persistence and passing CI tests | Run the console app manually, capture a real screenshot, and verify backup/restore behavior |
| Code Quest | [Code-Quest](https://github.com/parakandlabharath/Code-Quest) | Recovered React/TypeScript coding-practice app; CI passes | Secure isolated code execution, durable server-side learner data, end-to-end tests |
| PricePulse India | [Online-price-tracking](https://github.com/parakandlabharath/Online-price-tracking) | Recovered React/TypeScript watchlist app; CI passes | Verified retailer price source, historical observations, cross-device persistence, end-to-end tests |
| Task Collaboration | [Task-Collaboration](https://github.com/parakandlabharath/Task-Collaboration) | Recovered real-time React/Express prototype; CI passes | Authentication/authorization, database-backed persistence, WebSocket integration tests |

## Ten-point completion gate

A project is only called **finished** when all applicable checks are verified. A successful TypeScript check or build alone is not proof that the main features work.

| Check | Employee Management | Code Quest | PricePulse | Task Collaboration |
|---|---|---|---|---|
| Organized source | Done | Done | Done | Done |
| Application builds | CI pass | CI pass | CI pass | CI pass |
| Main features verified | CRUD/service tests pass; manual console run pending | Not complete: secure code runner disabled | Partial: watchlist works; live refresh disabled | Partial: event logic present; full browser integration pending |
| Data stored and retrieved | Local file, reload covered by tests | Browser local storage; leaderboard is in-memory | Browser local storage + JSON backup | Server JSON file |
| Validation/error handling | Employee fields and storage errors | Reward inputs and backup restore validated; old browser profiles still need schema migration | Imported and restored watchlists validated, including nested records and URL protocols | Nested WebSocket payload validation; no authenticated users |
| Important tests | JDK-only CRUD/persistence tests pass in CI | Reward, lesson idempotency, coin spending, backup round-trip tests in CI | Watchlist import and nested record validation tests in CI | Nested payload validation tests in CI |
| Accurate README/setup | Done | Done | Done | Done |
| Real running screenshots | Pending | Pending | Pending | Pending |
| GitHub Actions | Pass | Pass | Pass | Pass |
| Interview architecture explanation | Done in README | Done in README | Done in README | Done in README |

## What the recent GitHub updates changed

### Employee Management
- Added an `EmployeeRepository` abstraction and a file-backed implementation.
- Added safe text encoding and atomic file replacement.
- Added tests for create, duplicate rejection, search, update, delete, validation, and reload after restart.
- Updated the console application and setup guide.
- Local records are written to `projects/employee-management/data/employees.txt` and are excluded from Git.

### Code Quest
- Extracted challenge reward calculations into pure TypeScript functions.
- Made lesson rewards idempotent by lesson ID and tested duplicate completion and coin spending.
- Added validated JSON export/import for learner profiles and generated custom challenges.
- Clarified that arbitrary code execution is disabled until a secure sandbox is available.
- Updated the root README to describe the recovered React/Express application, not an unimplemented Spring Boot task manager.

### PricePulse India
- Added strict validation before importing or restoring a watchlist JSON backup, including nested history/store records and HTTP(S) URLs.
- Preserved human-readable legacy demo history labels so an exported demo watchlist can be restored.
- Removed generated fake 30/60-day historical observations when a product is first added.
- Updated the UI and README to describe AI results as estimates, not verified live retailer prices.

### Task Collaboration
- Added validation helpers and tests for task, project, comment, nested assignee/checklist, date, and status payloads.
- Scoped task and comment changes to the WebSocket connection's selected project.
- Used the connected session's demo identity for comment attribution.
- Added a WebSocket message-size limit.
- Added client-facing warnings when the server cannot save a workspace change to disk.
- Documented that preset identities are not authentication and the JSON file is prototype-level persistence.

## Important recovery notes

- The three recovered web apps are React/TypeScript + Express projects. Their previous root README files incorrectly described stacks or source states that did not match the recovered code. Those descriptions have been corrected.
- The recovered Code Quest codebase is a gamified coding-practice app; it is not currently the Java/Spring Boot team task manager that had been discussed earlier.
- **Mana Suraksha** was previously discussed as a concept and architecture, but no separate GitHub repository or application source was verified. It is not counted as an existing code repository here.
- The Placement Management System idea was declined, so it is not being created as a new project.
- The older single-file Employee Management exercise remains preserved under `mini-projects/employee-management-system/`; the structured, file-backed project under `projects/employee-management/` is the primary version.

## Next completion order

1. Manually run Employee Management, test it from a clean folder, and capture a genuine screenshot.
2. Add WebSocket integration tests and a browser run-through for Task Collaboration; plan authentication before any public deployment.
3. Decide whether Code Quest should remain a coding-practice product; only then implement a secure isolated judge.
4. Integrate a verified retailer price source into PricePulse before claiming automatic tracking or historical price trends.
5. Capture real screenshots for each app only after each one has been launched and its main user flow tested.

**Current conclusion:** all four repositories have updated source, documentation, and passing GitHub Actions validation for the latest verified commits (14 November 2026). They are not all production-finished: secure code execution, verified retailer feeds, real authentication, browser end-to-end tests, and actual application screenshots remain outstanding. No fake screenshots or unverified live-price features have been added.
