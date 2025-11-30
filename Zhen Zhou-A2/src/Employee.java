// Employee.java
/**
 * Represents a theme park employee who operates rides
 * Extends the Person class with employee-specific attributes
 */
public class Employee extends Person {
    private String employeeId;
    private String department;
    private boolean isAvailable;

    /**
     * Default constructor
     */
    public Employee() {
        super();
        this.employeeId = "E000";
        this.department = "Operations";
        this.isAvailable = true;
    }

    /**
     * Parameterized constructor
     * @param name Employee's name
     * @param age Employee's age
     * @param email Employee's email
     * @param employeeId Unique employee identifier
     * @param department Employee's department
     */
    public Employee(String name, int age, String email, String employeeId, String department) {
        super(name, age, email);
        this.employeeId = employeeId;
        this.department = department;
        this.isAvailable = true;
    }

    // Getter and Setter methods
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", employeeId='" + employeeId + '\'' +
                ", department='" + department + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}