// Ride.java
import java.util.*;
import java.io.*;

/**
 * Represents a theme park ride with queue management and history tracking
 * Implements RideInterface for standardized ride operations
 */
public class Ride implements RideInterface {
    private String rideName;
    private String rideType;
    private Employee operator;
    private int maxRider;
    private int numOfCycles;

    // Collections for queue and history
    private Queue<Visitor> waitingLine;
    private LinkedList<Visitor> rideHistory;

    /**
     * Default constructor
     */
    public Ride() {
        this.rideName = "Unknown Ride";
        this.rideType = "General";
        this.operator = null;
        this.maxRider = 2;
        this.numOfCycles = 0;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }

    /**
     * Parameterized constructor
     * @param rideName Name of the ride
     * @param rideType Type of the ride
     * @param operator Employee operating the ride
     * @param maxRider Maximum riders per cycle
     */
    public Ride(String rideName, String rideType, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        this.maxRider = maxRider;
        this.numOfCycles = 0;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }

    // Getter and Setter methods
    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        if (maxRider > 0) {
            this.maxRider = maxRider;
        } else {
            System.out.println("Error: maxRider must be positive");
        }
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    // Interface method implementations
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingLine.add(visitor);
            System.out.println("Successfully added visitor " + visitor.getName() + " to " + rideName + " waiting queue");
        } else {
            System.out.println("Error: Visitor object is null");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        if (!waitingLine.isEmpty()) {
            Visitor removed = waitingLine.poll();
            System.out.println("Removed visitor from queue: " + removed.getName());
        } else {
            System.out.println("Queue is empty, cannot remove visitor");
        }
    }

    @Override
    public void printQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println(rideName + " - Waiting queue is empty");
            return;
        }

        System.out.println(rideName + " - Current waiting queue (" + waitingLine.size() + " visitors):");
        int position = 1;
        for (Visitor visitor : waitingLine) {
            System.out.println(position + ". " + visitor.getName() + " (ID: " + visitor.getVisitorId() + ")");
            position++;
        }
    }

    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null && !rideHistory.contains(visitor)) {
            rideHistory.add(visitor);
            System.out.println("Successfully added visitor " + visitor.getName() + " to " + rideName + " history");
        } else if (visitor == null) {
            System.out.println("Error: Visitor object is null");
        } else {
            System.out.println("Visitor " + visitor.getName() + " is already in history");
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Error: Visitor object is null");
            return false;
        }

        boolean found = rideHistory.contains(visitor);
        System.out.println("Visitor " + visitor.getName() + " in history: " + found);
        return found;
    }

    @Override
    public int numberOfVisitors() {
        System.out.println(rideName + " has " + rideHistory.size() + " visitors in history");
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println(rideName + " - History is empty");
            return;
        }

        System.out.println(rideName + " - Ride history (" + rideHistory.size() + " visitors):");
        Iterator<Visitor> iterator = rideHistory.iterator();
        int count = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println(count + ". " + visitor.getName() + " (ID: " + visitor.getVisitorId() +
                    ", Age: " + visitor.getAge() + ", Ticket: " + visitor.getTicketType() + ")");
            count++;
        }
    }

    @Override
    public void runOneCycle() {
        // Check if operator is available
        if (operator == null) {
            System.out.println("Error: No operator assigned to " + rideName);
            return;
        }

        if (!operator.isAvailable()) {
            System.out.println("Error: Operator " + operator.getName() + " is not available for " + rideName);
            return;
        }

        // Check if waiting queue is empty
        if (waitingLine.isEmpty()) {
            System.out.println("Error: Waiting queue is empty, cannot run " + rideName);
            return;
        }

        System.out.println("=== Running one cycle of " + rideName + " ===");
        System.out.println("Operator: " + operator.getName());
        System.out.println("Available riders this cycle: " + Math.min(maxRider, waitingLine.size()));

        int ridersThisCycle = Math.min(maxRider, waitingLine.size());

        for (int i = 0; i < ridersThisCycle; i++) {
            Visitor currentVisitor = waitingLine.poll();
            if (currentVisitor != null) {
                addVisitorToHistory(currentVisitor);
                System.out.println("Visitor " + currentVisitor.getName() + " completed the ride");
            }
        }

        numOfCycles++;
        System.out.println("Cycle completed. Total cycles run: " + numOfCycles);
        System.out.println("Remaining visitors in queue: " + waitingLine.size());
    }

    /**
     * Sorts the ride history using the provided comparator
     * @param comparator The comparator to use for sorting
     */
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (rideHistory.size() > 1) {
            Collections.sort(rideHistory, comparator);
            System.out.println("History sorted successfully using specified criteria");
        } else {
            System.out.println("Not enough visitors in history to sort");
        }
    }

    /**
     * Exports ride history to a CSV file
     * @param filename The name of the file to export to
     */
    public void exportRideHistory(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Visitor visitor : rideHistory) {
                writer.println(visitor.getName() + "," +
                        visitor.getAge() + "," +
                        visitor.getEmail() + "," +
                        visitor.getVisitorId() + "," +
                        visitor.getTicketType());
            }
            System.out.println("Successfully exported " + rideHistory.size() + " visitors to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error exporting file: " + e.getMessage());
        }
    }

    /**
     * Imports ride history from a CSV file
     * @param filename The name of the file to import from
     */
    public void importRideHistory(String filename) {
        int importedCount = 0;
        int duplicateCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    try {
                        String name = parts[0];
                        int age = Integer.parseInt(parts[1]);
                        String email = parts[2];
                        String visitorId = parts[3];
                        String ticketType = parts[4];

                        Visitor visitor = new Visitor(name, age, email, visitorId, ticketType);
                        if (!rideHistory.contains(visitor)) {
                            rideHistory.add(visitor);
                            importedCount++;
                        } else {
                            duplicateCount++;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: Invalid age format in line: " + line);
                    }
                } else {
                    System.out.println("Warning: Invalid data format in line: " + line);
                }
            }
            System.out.println("Import completed: " + importedCount + " new visitors imported, " +
                    duplicateCount + " duplicates skipped");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideName='" + rideName + '\'' +
                ", rideType='" + rideType + '\'' +
                ", operator=" + (operator != null ? operator.getName() : "None") +
                ", maxRider=" + maxRider +
                ", numOfCycles=" + numOfCycles +
                ", waitingVisitors=" + waitingLine.size() +
                ", historyVisitors=" + rideHistory.size() +
                '}';
    }
}