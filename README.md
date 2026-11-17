# CertificateTracker

A beginner-friendly Java console application to keep track of professional certificates, their issuers, issue dates, and expiration dates.

## Features

- Add a certificate
- View all saved certificates during the current run
- Identify expired certificates and certificates expiring within 30 days
- Search by certificate name or issuer
- Delete a certificate by ID
- View a summary of active, expiring, and expired certificates
- Validate dates and required text fields

## Built With

- Java
- ArrayList
- Classes and objects
- Methods
- Loops and conditional statements
- Scanner for user input
- `LocalDate` and `ChronoUnit` for date handling

## Requirements

- JDK 8 or later

## How to Run

Open a terminal in the project folder.

### macOS / Linux

```bash
javac -d out src/*.java
java -cp out CertificateTracker
```

### Windows

```bash
javac -d out src\\*.java
java -cp out CertificateTracker
```

## Expiration Status Rules

- **EXPIRED** — expiration date is before today
- **EXPIRES TODAY** — expiration date is today
- **EXPIRING SOON** — expiration is within the next 30 days
- **ACTIVE** — expiration is more than 30 days away

## Note

This starter version stores certificates in memory while the program is running. The list is cleared when you exit the application. File or database storage can be added as a future enhancement.

## Example

```text
==================================
       CERTIFICATE TRACKER
==================================

1. Add Certificate
2. View All Certificates
3. View Expiring / Expired Certificates
4. Search Certificate
5. Delete Certificate
6. View Summary
0. Exit
```

## Future Improvements

- Save certificate records to a file
- Add edit/update functionality
- Export certificate details to CSV
- Add reminder notifications
