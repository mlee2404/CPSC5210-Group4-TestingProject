#!/bin/bash

# Usage: ./regressionTest.sh recipient_email@example.com
EMAIL=$1

# Ensure EMAIL_USER and EMAIL_PASS are set
if [ -z "$EMAIL_USER" ]; then
  echo "Error: EMAIL_USER environment variable is not set."
  exit 1
fi

if [ -z "$EMAIL_PASS" ]; then
  echo "Error: EMAIL_PASS environment variable is not set."
  exit 1
fi

# Set Project Root (assumes script is run from the project root)
PROJECT_ROOT=$(pwd)

# Set CLASSPATH relative to the project directory
export CLASSPATH="$PROJECT_ROOT/out/production/CPSC5210-Group4-TestingProject:$PROJECT_ROOT/lib/junit-platform-console-standalone-1.8.1.jar"

# Compile source files
echo "Compiling source files..."
javac --release 11 -cp "$CLASSPATH" \
  -d "$PROJECT_ROOT/out/production/CPSC5210-Group4-TestingProject" \
  "$PROJECT_ROOT/src/othello/"*.java "$PROJECT_ROOT/src/Test/"*.java > build.log 2>&1
if [ $? -ne 0 ]; then
  echo "=== Build Failed ==="
  cat build.log
  exit 1
fi

# Run all tests
echo "Running all tests..."
java -cp "$CLASSPATH" org.junit.platform.console.ConsoleLauncher --scan-classpath > test.log 2>&1
if [ $? -ne 0 ]; then
  echo "=== Test Execution Failed ==="
fi

# Extract concise summary
echo "Extracting concise summary..."
grep -E "tests found|tests successful|tests failed" test.log > summary.log

# Debugging summary.log
echo "===== Debugging summary.log ====="
cat summary.log
echo "================================="

# Create email content with the desired format in a temporary file
EMAIL_BODY_FILE=$(mktemp)
{
  echo "Subject: Test Summary Report"
  echo "To: $EMAIL"
  echo
  echo "Test Summary Report"
  echo
  echo "Build Status: True"
  echo "Test Status: True"
  echo
  cat summary.log
} > "$EMAIL_BODY_FILE"

# Debugging email content
echo "===== Debugging email content ====="
cat "$EMAIL_BODY_FILE"
echo "==================================="

# Send the email with subject
echo "Sending summary report to $EMAIL..."
curl -s --url "smtps://smtp.gmail.com:465" \
  --ssl-reqd \
  --mail-from "$EMAIL_USER" \
  --mail-rcpt "$EMAIL" \
  --user "$EMAIL_USER:$EMAIL_PASS" \
  -T "$EMAIL_BODY_FILE"

if [ $? -eq 0 ]; then
  echo "Email successfully sent to $EMAIL."
else
  echo "Failed to send email to $EMAIL."
fi

# Clean up the temporary file
rm "$EMAIL_BODY_FILE"

echo "Process complete. Check test.log and summary.log for details."
