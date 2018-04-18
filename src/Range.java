import java.util.Date;

/**
 * A date range from start to end
 */
public class Range {
    Date start, end;

    Range(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return this.start + " -> " + this.end;
    }
}