<#
.SYNOPSIS
    Helper script to initialize a git repository (if needed), add remote, commit and push to GitHub.

USAGE
    .\push-to-github.ps1
    .\push-to-github.ps1 -RepoPath 'C:\path\to\repo' -Remote 'https://github.com/owner/repo.git' -UserName 'Your Name' -UserEmail 'you@example.com'

#>

Param(
    [string]$RepoPath = (Get-Location).Path,
    [string]$Remote = 'https://github.com/rrumi25800/beauty_with_rumi_e-commerce.git',
    [string]$UserName = '',
    [string]$UserEmail = ''
)

function Exit-WithError($msg, $code = 1) {
    Write-Error $msg
    exit $code
}

if (-not (Get-Command git -ErrorAction SilentlyContinue)) {
    Exit-WithError "Git is not installed or not found in PATH. Install Git for Windows: https://git-scm.com/download/win"
}

Write-Output "Using repository path: $RepoPath"

Push-Location $RepoPath
try {
    # Initialize repo if needed
    if (-not (Test-Path .git)) {
        Write-Output "Initializing git repository..."
        git init
        if ($LASTEXITCODE -ne 0) { Exit-WithError 'Failed to initialize git repository.' }
    } else {
        Write-Output "Git repository already initialized."
    }

    # Remove existing origin if present
    git remote get-url origin 2>$null | Out-Null
    if ($LASTEXITCODE -eq 0) {
        $existing = git remote get-url origin 2>$null
        Write-Output "Found existing origin: $existing -- removing it."
        git remote remove origin
        if ($LASTEXITCODE -ne 0) { Exit-WithError 'Failed to remove existing origin.' }
    }

    Write-Output "Adding remote origin: $Remote"
    git remote add origin $Remote
    if ($LASTEXITCODE -ne 0) { Exit-WithError "Failed to add remote origin $Remote" }

    # Optionally set local user.name/email
    if ($UserName -ne '') {
        git config user.name "$UserName"
        Write-Output "Set local git user.name to $UserName"
    }
    if ($UserEmail -ne '') {
        git config user.email "$UserEmail"
        Write-Output "Set local git user.email to $UserEmail"
    }

    Write-Output "Staging files..."
    git add .
    if ($LASTEXITCODE -ne 0) { Exit-WithError 'Failed to stage files.' }

    # Create commit only if there are staged changes
    git diff --cached --quiet
    if ($LASTEXITCODE -ne 0) {
        Write-Output "Creating initial commit..."
        git commit -m "Initial commit"
        if ($LASTEXITCODE -ne 0) { Exit-WithError 'Failed to commit changes.' }
    } else {
        Write-Output "No staged changes to commit."
    }

    Write-Output "Ensuring branch name is 'main'..."
    git branch -M main
    if ($LASTEXITCODE -ne 0) { Exit-WithError "Failed to rename/set branch to 'main'" }

    Write-Output "Pushing to remote origin/main (you may be prompted for credentials)..."
    git push -u origin main
    if ($LASTEXITCODE -ne 0) {
        Exit-WithError 'Push failed. Check authentication (use PAT) or if the remote already has unrelated history.'
    }

    Write-Output "Push successful. Open the repository URL to verify: $Remote"
} finally {
    Pop-Location
}

Write-Output "Script finished."
