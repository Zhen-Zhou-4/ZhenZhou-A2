// AssignmentTwo.java
import java.util.*;

public class AssignmentTwo {
    public static void main(String[] args) {
        System.out.println("=== Theme Park Management System ===");

        AssignmentTwo assignment = new AssignmentTwo();

        // Run demonstrations for each part
        assignment.partThree();
        assignment.partFourA();
        assignment.partFourB();
        assignment.partFive();
        assignment.partSix();
        assignment.partSeven();
    }

    public void partThree() {
        System.out.println("\n=== Part 3: Waiting Queue Demonstration ===");

        // Create operator
        Employee operator = new Employee("John Smith", 28, "john@park.com", "E001", "Ride Operations");

        // Create ride
        Ride rollerCoaster = new Ride("Roller Coaster", "Thrill Ride", operator, 2);

        // Create visitors and add to queue
        Visitor[] visitors = {
                new Visitor("Alice Brown", 25, "alice@email.com", "V001", "Standard"),
                new Visitor("Bob Wilson", 30, "bob@email.com", "V002", "Premium"),
                new Visitor("Carol Davis", 22, "carol@email.com", "V003", "Standard"),
                new Visitor("David Miller", 35, "david@email.com", "V004", "Standard"),
                new Visitor("Eva Garcia", 28, "eva@email.com", "V005", "Premium")
        };

        // Add visitors to queue
        for (Visitor visitor : visitors) {
            rollerCoaster.addVisitorToQueue(visitor);
        }

        // Print queue
        rollerCoaster.printQueue();

        // Remove one visitor
        rollerCoaster.removeVisitorFromQueue();

        // Print queue again
        rollerCoaster.printQueue();
    }

    public void partFourA() {
        System.out.println("\n=== Part 4A: Ride History Demonstration ===");

        Employee operator = new Employee("Operator A", 30, "op@park.com", "E002", "Operations");
        Ride waterRide = new Ride("Water Adventure", "Water Ride", operator, 4);

        // Create visitors
        Visitor[] visitors = {
                new Visitor("Visitor One", 20, "v1@email.com", "V101", "Standard"),
                new Visitor("Visitor Two", 25, "v2@email.com", "V102", "Premium"),
                new Visitor("Visitor Three", 18, "v3@email.com", "V103", "Standard"),
                new Visitor("Visitor Four", 32, "v4@email.com", "V104", "Standard"),
                new Visitor("Visitor Five", 27, "v5@email.com", "V105", "Premium")
        };

        // Add visitors to history
        for (Visitor visitor : visitors) {
            waterRide.addVisitorToHistory(visitor);
        }

        // Check specific visitor
        waterRide.checkVisitorFromHistory(visitors[2]);

        // Print number of visitors
        waterRide.numberOfVisitors();

        // Print history
        waterRide.printRideHistory();
    }

    public void partFourB() {
        System.out.println("\n=== Part 4B: History Sorting Demonstration ===");

        Employee operator = new Employee("Operator B", 29, "op2@park.com", "E003", "Operations");
        Ride ferrisWheel = new Ride("Ferris Wheel", "Sightseeing", operator, 6);

        // Create visitors with some same names but different ages for sorting test
        Visitor[] visitors = {
                new Visitor("John Smith", 25, "john1@email.com", "V201", "Standard"),
                new Visitor("Mary Johnson", 30, "mary@email.com", "V202", "Premium"),
                new Visitor("John Smith", 20, "john2@email.com", "V203", "Standard"),
                new Visitor("Robert Brown", 35, "robert@email.com", "V204", "Standard"),
                new Visitor("Lisa Davis", 28, "lisa@email.com", "V205", "Premium")
        };

        // Add visitors to history
        for (Visitor visitor : visitors) {
            ferrisWheel.addVisitorToHistory(visitor);
        }

        System.out.println("Before sorting:");
        ferrisWheel.printRideHistory();

        // Create comparator and sort
        VisitorComparator comparator = new VisitorComparator();
        ferrisWheel.sortRideHistory(comparator);

        System.out.println("After sorting:");
        ferrisWheel.printRideHistory();
    }

    public void partFive() {
        System.out.println("\n=== Part 5: Ride Cycle Demonstration ===");

        Employee operator = new Employee("Cycle Operator", 26, "cycle@park.com", "E004", "Ride Operations");
        Ride thunderCoaster = new Ride("Thunder Coaster", "Thrill Ride", operator, 3);

        // Create 10 visitors
        Visitor[] visitors = new Visitor[10];
        for (int i = 0; i < visitors.length; i++) {
            visitors[i] = new Visitor("Queue Visitor " + (i+1), 20 + i,
                    "visitor" + (i+1) + "@email.com",
                    "V30" + i, i % 2 == 0 ? "Standard" : "Premium");
        }

        // Add all visitors to queue
        for (Visitor visitor : visitors) {
            thunderCoaster.addVisitorToQueue(visitor);
        }

        System.out.println("Queue before running cycle:");
        thunderCoaster.printQueue();

        // Run one cycle
        thunderCoaster.runOneCycle();

        System.out.println("Queue after running cycle:");
        thunderCoaster.printQueue();

        System.out.println("Ride history:");
        thunderCoaster.printRideHistory();

        // Run another cycle
        thunderCoaster.runOneCycle();
    }

    public void partSix() {
        System.out.println("\n=== Part 6: File Export Demonstration ===");

        Employee operator = new Employee("File Operator", 27, "file@park.com", "E005", "Operations");
        Ride exportRide = new Ride("Export Test Ride", "Test Ride", operator, 2);

        // Create and add visitors
        Visitor[] visitors = {
                new Visitor("Export Visitor 1", 22, "export1@email.com", "V401", "Standard"),
                new Visitor("Export Visitor 2", 29, "export2@email.com", "V402", "Premium"),
                new Visitor("Export Visitor 3", 31, "export3@email.com", "V403", "Standard"),
                new Visitor("Export Visitor 4", 24, "export4@email.com", "V404", "Standard"),
                new Visitor("Export Visitor 5", 26, "export5@email.com", "V405", "Premium")
        };

        for (Visitor visitor : visitors) {
            exportRide.addVisitorToHistory(visitor);
        }

        // Export to file
        exportRide.exportRideHistory("ride_history.csv");
    }

    public void partSeven() {
        System.out.println("\n=== Part 7: File Import Demonstration ===");

        Employee operator = new Employee("Import Operator", 28, "import@park.com", "E006", "Operations");
        Ride importRide = new Ride("Import Test Ride", "Test Ride", operator, 2);

        // Import history from file
        importRide.importRideHistory("ride_history.csv");

        // Verify import results
        importRide.numberOfVisitors();
        importRide.printRideHistory();
    }
}