# Git and GitHub Fundamentals

A practical reference for the Git and GitHub concepts practised during learning.

## 1. Git vs GitHub

- **Git** is a distributed version control system. It records project history through commits.
- **GitHub** is a hosting and collaboration platform for Git repositories. It adds pull requests, issues, code review and automation.

## 2. Core terminology

| Term | Meaning |
| --- | --- |
| Repository (repo) | Project files plus their version history |
| Working directory | Files currently being edited |
| Staging area | Changes selected for the next commit |
| Commit | A saved snapshot in the local history |
| Branch | An independent line of development |
| Remote | A named copy of a repository hosted elsewhere |
| Push | Send local commits to a remote |
| Pull | Fetch remote changes and integrate them into the current branch |
| Clone | Download a repository and its history |
| Fork | Create your own GitHub-side copy of another repository |
| Merge | Combine changes from one branch into another |
| Pull request (PR) | A GitHub proposal to review and merge changes |
| Merge conflict | A situation where Git needs help reconciling overlapping edits |

## 3. The everyday workflow

```text
Edit files
   |
   v
git status
   |
   v
git add <file>
   |
   v
git commit -m "Describe the change"
   |
   v
git push
   |
   v
Verify the commit on GitHub
```

Commands:

```bash
# Check the current branch and changed files
git status

# Stage one file (safer than staging everything)
git add path/to/file

# Save a local snapshot
git commit -m "Add system design fundamentals notes"

# Send commits to GitHub
git push
```

For a first push of a new branch, Git may ask you to set its upstream:

```bash
git push -u origin feature/system-design-notes
```

## 4. Branching

Branches let you work on a change without immediately modifying the main branch.

```bash
# Update local main first
git switch main
git pull

# Create and switch to a feature branch
git switch -c feature/login

# Work, stage and commit
git add .
git commit -m "Add login feature"

# Push the branch
git push -u origin feature/login
```

Then open a pull request on GitHub to review and merge the branch into `main`.

Example branch names:

- `main`: stable shared branch.
- `feature/login`: new login work.
- `feature/payment`: payment feature.
- `fix/validation-error`: focused bug fix.

## 5. Merge conflicts

A conflict occurs when Git cannot automatically combine edits, often because two branches changed the same lines.

A safe resolution process:

1. Read the conflict markers in each affected file.
2. Decide which logic should remain; combine both changes when appropriate.
3. Remove conflict markers and save the file.
4. Run tests or compile the affected code.
5. Stage the resolved file and commit the merge.

Never resolve a conflict by blindly deleting one side without understanding the code.

## 6. Pull requests and code review

A pull request (PR) is a review conversation around proposed changes. A useful PR includes:

- A clear title and summary.
- Why the change is needed.
- Files or behaviour affected.
- Test steps and results.
- Known limitations.

A PR is not the same thing as a commit: a commit records a change; a PR proposes reviewing and integrating a branch.

## 7. GitHub Actions and CI/CD

- **GitHub Actions** runs automated workflows in response to events such as pushes and pull requests.
- **CI (Continuous Integration):** automatically build and test changes so errors are found early.
- **CD (Continuous Delivery/Deployment):** automate preparing or releasing validated software; deployment may require an approval step.

Example workflow:

```text
Developer pushes code
        |
        v
GitHub Actions starts
        |
        v
Install JDK / dependencies
        |
        v
Compile and run tests
        |
   +----+----+
   |         |
 Pass      Fail
   |         |
   v         v
Review /   Inspect logs,
merge      fix and push again
```

Automation does not guarantee software is correct; it checks the steps that have actually been configured.

## 8. Useful commands

```bash
git clone <repository-url>
git branch
git switch <branch-name>
git switch -c <new-branch-name>
git log --oneline
git diff
git pull
git push
git remote -v
```

Use `git diff` before staging to review edits. Use `git log --oneline` to inspect recent commits.

## 9. Good habits

- Write small commits with meaningful messages.
- Commit related changes together.
- Pull before starting work on a shared branch.
- Use branches for larger features or experiments.
- Review changes before committing.
- Never commit passwords, API keys, private tokens or personal data.
- Add generated files and local configuration to `.gitignore` when appropriate.
- Verify that the push succeeded and the expected files appear on GitHub.

## Interview quick answers

- **Why Git?** To track changes, collaborate and restore earlier versions.
- **Commit vs push?** Commit saves a snapshot in local history; push sends commits to a remote.
- **Branch vs fork?** A branch is a line of development within a repository; a fork is a separate GitHub copy.
- **Pull vs clone?** Clone creates a local copy; pull updates an existing local branch from its remote.
- **What is CI?** Automated build and test checks on code changes.
