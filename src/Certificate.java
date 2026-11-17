import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Certificate {
    private int id;
    private String name;
    private String issuer;
    private LocalDate issueDate;
    private LocalDate expirationDate;

    public Certificate(int id, String name, String issuer,
                       LocalDate issueDate, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.issuer = issuer;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIssuer() {
        return issuer;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getStatus() {
        LocalDate today = LocalDate.now();

        if (expirationDate.isBefore(today)) {
            return "EXPIRED";
        } else if (expirationDate.isEqual(today)) {
            return "EXPIRES TODAY";
        } else if (ChronoUnit.DAYS.between(today, expirationDate) <= 30) {
            return "EXPIRING SOON";
        }
        return "ACTIVE";
    }

    public long getDaysUntilExpiration() {
        return ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
    }

    public void display() {
        System.out.println("----------------------------------------");
        System.out.println("ID: " + id);
        System.out.println("Certificate: " + name);
        System.out.println("Issuer: " + issuer);
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Expiration Date: " + expirationDate);
        System.out.println("Status: " + getStatus());
        if (getDaysUntilExpiration() >= 0) {
            System.out.println("Days Remaining: " + getDaysUntilExpiration());
        } else {
            System.out.println("Expired " + Math.abs(getDaysUntilExpiration()) + " day(s) ago");
        }
    }
}
