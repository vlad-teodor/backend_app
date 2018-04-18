import java.util.Date;
import java.util.Random;

public class Place {
    String name;
    Loc location;
    double dailyPrice;
    Range period;

    public Place(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + this.name +
                "\nLocation: " + this.location +
                "\nDaily price: " + this.dailyPrice +
                "\nTime period: " + this.period + "\n";
    }
}