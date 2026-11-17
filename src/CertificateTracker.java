import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class CertificateTracker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Certificate> certificates = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("==================================");
        System.out.println("       CERTIFICATE TRACKER");
        System.out.println("==================================");

        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addCertificate();
                    break;
                case 2:
                    viewAllCertificates();
                    break;
                case 3:
                    viewExpiringCertificates();
                    break;
                case 4:
                    searchCertificate();
                    break;
                case 5:
                    deleteCertificate();
                    break;
                case 6:
                    showSummary();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using Certificate Tracker!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n--------------- MENU ---------------");
        System.out.println("1. Add Certificate");
        System.out.println("2. View All Certificates");
        System.out.println("3. View Expiring / Expired Certificates");
        System.out.println("4. Search Certificate");
        System.out.println("5. Delete Certificate");
        System.out.println("6. View Summary");
        System.out.println("0. Exit");
        System.out.println("------------------------------------");
    }

    private static void addCertificate() {
        System.out.println("\n--- Add Certificate ---");
        String name = readText("Certificate name: ");
        String issuer = readText("Issued by: ");
        LocalDate issueDate = readDate("Issue date (YYYY-MM-DD): ");
        LocalDate expirationDate = readDate("Expiration date (YYYY-MM-DD): ");

        if (expirationDate.isBefore(issueDate)) {
            System.out.println("Expiration date cannot be before issue date.");
            return;
        }

        Certificate certificate = new Certificate(
                nextId++, name, issuer, issueDate, expirationDate);
        certificates.add(certificate);
        System.out.println("Certificate added successfully!");
    }

    private static void viewAllCertificates() {
        System.out.println("\n--- All Certificates ---");
        if (certificates.isEmpty()) {
            System.out.println("No certificates found. Add one first.");
            return;
        }

        for (Certificate certificate : certificates) {
            certificate.display();
        }
    }

    private static void viewExpiringCertificates() {
        System.out.println("\n--- Expiring / Expired Certificates ---");
        boolean found = false;

        for (Certificate certificate : certificates) {
            String status = certificate.getStatus();
            if (status.equals("EXPIRED") || status.equals("EXPIRES TODAY")
                    || status.equals("EXPIRING SOON")) {
                certificate.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expiring or expired certificates found.");
        }
    }

    private static void searchCertificate() {
        System.out.println("\n--- Search Certificate ---");
        String keyword = readText("Enter certificate name or issuer: ").toLowerCase();
        boolean found = false;

        for (Certificate certificate : certificates) {
            if (certificate.getName().toLowerCase().contains(keyword)
                    || certificate.getIssuer().toLowerCase().contains(keyword)) {
                certificate.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching certificate found.");
        }
    }

    private static void deleteCertificate() {
        System.out.println("\n--- Delete Certificate ---");
        int id = readInt("Enter certificate ID to delete: ");

        for (int i = 0; i < certificates.size(); i++) {
            if (certificates.get(i).getId() == id) {
                certificates.remove(i);
                System.out.println("Certificate deleted successfully.");
                return;
            }
        }

        System.out.println("Certificate ID not found.");
    }

    private static void showSummary() {
        int active = 0;
        int expiring = 0;
        int expired = 0;

        for (Certificate certificate : certificates) {
            String status = certificate.getStatus();
            if (status.equals("ACTIVE")) {
                active++;
            } else if (status.equals("EXPIRED")) {
                expired++;
            } else {
                expiring++;
            }
        }

        System.out.println("\n--- Certificate Summary ---");
        System.out.println("Total Certificates: " + certificates.size());
        System.out.println("Active: " + active);
        System.out.println("Expiring Soon / Today: " + expiring);
        System.out.println("Expired: " + expired);
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Use YYYY-MM-DD (example: 2026-12-31).");
            }
        }
    }
}
