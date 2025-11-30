// VisitorComparator.java
import java.util.Comparator;

/**
 * Comparator implementation for sorting Visitor objects
 * Compares by name first, then by age if names are equal
 */
public class VisitorComparator implements Comparator<Visitor> {

    /**
     * Compares two visitors for sorting
     * @param v1 First visitor to compare
     * @param v2 Second visitor to compare
     * @return Negative if v1 < v2, positive if v1 > v2, zero if equal
     */
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // First compare by name
        int nameCompare = v1.getName().compareToIgnoreCase(v2.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }

        // If names are equal, compare by age
        return Integer.compare(v1.getAge(), v2.getAge());
    }
}