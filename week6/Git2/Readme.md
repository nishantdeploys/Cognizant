# Git Ignore Log Files and Log Directories

## Objective

The objective of this exercise is to configure Git to ignore log files (`*.log`) and log directories (`logs/`) using the `.gitignore` file and verify the behavior using Git commands.

---

## Prerequisites

* Git installed
* Git Bash configured
* A Git repository initialized

---

## Step 1: Create a Log Directory

Run the following command:

```bash
mkdir logs
```

### Screenshot

*Insert screenshot here*

---

## Step 2: Create Log Files

Create sample log files:

```bash
touch app.log
touch error.log
touch logs/server.log
```

Verify:

```bash
find . -name "*.log"
```

### Screenshot

*Insert screenshot here*

---

## Step 3: Create and Configure .gitignore

Create or edit the `.gitignore` file:

```bash
nano .gitignore
```

Add the following content:

```gitignore
# Ignore all log files
*.log

# Ignore log directory
logs/
```

Save and exit.

### Screenshot

*Insert screenshot of .gitignore file here*

---

## Step 4: Verify Ignored Files

Run:

```bash
git status --ignored
```

Expected Result:

* `.log` files should appear under **Ignored files**
* `logs/` directory should appear under **Ignored files**

### Screenshot

*Insert screenshot here*

---

## Step 5: Check Repository Status

Run:

```bash
git status
```

Expected Result:

* `.gitignore` should appear as an untracked file.
* Log files and log folders should not appear.

### Screenshot

*Insert screenshot here*

---

## Step 6: Stage .gitignore

```bash
git add .gitignore
```

Verify:

```bash
git status
```

### Screenshot

*Insert screenshot here*

---

## Step 7: Commit Changes

```bash
git commit -m "Add gitignore for log files and log directories"
```

### Screenshot

*Insert screenshot here*

---

## Step 8: Push Changes to GitHub

```bash
git push origin main
```

> Replace `main` with your branch name if different.

### Screenshot

*Insert screenshot here*

---

## Step 9: Verify Working Tree

Run:

```bash
git status
```

Expected Output:

```text
On branch main
nothing to commit, working tree clean
```

### Screenshot

*Insert screenshot here*

---

## Git Commands Used

```bash
mkdir logs

touch app.log
touch error.log
touch logs/server.log

nano .gitignore

git status --ignored

git status

git add .gitignore

git commit -m "Add gitignore for log files and log directories"

git push origin main
```

---

## Conclusion

In this exercise, a `.gitignore` file was configured to exclude log files and log directories from version control. The configuration was verified using `git status` and `git status --ignored`, ensuring that unnecessary log artifacts are not committed to the repository.
