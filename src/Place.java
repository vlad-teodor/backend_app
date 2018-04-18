import java.util.Date;
import java.util.Random;

public class Place {
    String name;
    Loc location;
    double dailyPrice;
    Range period;
    String[] activities;

    public Place(String name) {
        this.name = name;
    }

    public Place addPrice(double price) {
        this.dailyPrice = price;
        return this;
    }

    public Place addLocation(Loc location) {
        this.location = location;
        return this;
    }

    public Place addPeriod(Range period) {
        this.period = period;
        return this;
    }

    public Place addActivities(String[] activities) {
        this.activities = activities;
        return this;
    }

    String getActivities() {
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