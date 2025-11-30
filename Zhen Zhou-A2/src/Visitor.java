// Visitor.java
import java.util.Objects;

/**
 * Represents a theme park visitor
 * Extends the Person class with visitor-specific attributes
 */
public class Visitor extends Person {
    private String visitorId;
    private String ticketType;
    private boolean hasPremiumPass;

    /**
     * Default constructor
     */
    public Visitor() {
        super();
        this.visitorId = "V000";
        this.ticketType = "Standard";
        this.hasPremiumPass = false;
    }

    /**
     * Parameterized constructor
     * @param name Visitor's name
     * @param age Visitor's age
     * @param email Visitor's email
     * @param visitorId Unique visitor identifier
     * @param ticketType Type of ticket (Standard/Premium)
     */
    public Visitor(String name, int age, String email, String visitorId, String ticketType) {
        super(name, age, email);
        this.visitorId = visitorId;
        this.ticketType = ticketType;
        this.hasPremiumPass = "Premium".equals(ticketType);
    }

    // Getter and Setter methods
    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
        this.hasPremiumPass = "Premium".equals(ticketType);
    }

    public boolean hasPremiumPass() {
        return hasPremiumPass;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Visitor visitor = (Visitor) obj;
        return Objects.equals(visitorId, visitor.visitorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), visitorId);
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", visitorId='" + visitorId + '\'' +
                ", ticketType='" + ticketType + '\'' +
                '}';
    }
}