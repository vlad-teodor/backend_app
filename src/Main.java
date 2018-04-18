import java.util.Random;

public class Main {

    static int PLACES = 10000;

    public static void main(String[] args) {
        Place[] places = new Place[PLACES];
        for (int i = 0; i < places.length; i++) {
            places[i] = new Place(RandomGenerator.getName())
                    .addPrice(RandomGenerator.getPrice())
                    .addLocation(new Loc(RandomGenerator.getCity(),
                            RandomGenerator.getCounty(),
                            RandomGenerator.getCountry()))
                    .addPeriod(RandomGenerator.getRange())
                    .addActivities(RandomGenerator.getActivities());
        }
        for (Place p : places) {
            System.out.println(p);
        }
    }
}
