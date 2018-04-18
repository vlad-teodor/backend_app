/**
 * A place containing name, location, price per day, period in which it can be visited and the activities possible
 */
public class Place {
    String name;
    City location;
    double dailyPrice;
    Range period;
    private String[] activities;

    Place(String name) {
        this.name = name;
    }

    Place addPrice(double price) {
        this.dailyPrice = price;
        return this;
    }

    Place addLocation(City location) {
        this.location = location;
        return this;
    }

    Place addPeriod(Range period) {
        this.period = period;
        return this;
    }

    Place addActivities(String[] activities) {
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
            if (!activity.equals(this.activities[this.activities.length - 1]))
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