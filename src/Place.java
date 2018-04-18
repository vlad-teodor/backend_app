import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A place containing name, location, price per day, period in which it can be visited and the activities possible
 */
public class Place {
    String name; // the name of the place
    City location; // the location (City/County/Country)
    double dailyPrice; // the daily price for the place
    Range period; // the period in which the place can be visited
    ArrayList<String> activities; // the activities possible in the place

    Place(String name) {
        this.name = name;
    }

    /**
     * Adds a price and returns the instance
     */
    Place addPrice(double price) {
        this.dailyPrice = price;
        return this;
    }

    /**
     * Adds a location and returns the instance
     */
    Place addLocation(City location) {
        this.location = location;
        return this;
    }

    /**
     * Adds a period and returns the instance
     */
    Place addPeriod(Range period) {
        this.period = period;
        return this;
    }

    /**
     * Adds a list of activities and returns the instance
     */
    Place addActivities(ArrayList<String> activities) {
        this.activities = activities;
        return this;
    }

    /**
     * @return a formatted string with the activities possible in this place
     */
    private String getActivities() {
        StringBuilder sb = new StringBuilder();
        for (String activity : this.activities) {
            sb.append(activity);
            // also append ", " if it's not the final element
            if (!activity.equals(this.activities.get(this.activities.size() - 1)))
                sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Name: " + this.name +
                "\nLocation: " + this.location +
                "\nDaily price: " + this.dailyPrice +
                "\nTime period: " + this.period +
                "\nActivities: " + getActivities() + "\n";
    }
}