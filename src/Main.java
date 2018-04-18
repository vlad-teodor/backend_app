public class Main {

    static int PLACES = 10000;

    public static void main(String[] args) {
        Place[] places = new Place[PLACES];
        PlaceContainer placesC = new PlaceContainer();
        for (int i = 0; i < places.length; i++) {
            do {
                places[i] = new Place(RandomGenerator.getName());
            } while (!placesC.insert(places[i]));

            places[i] = places[i].addPrice(RandomGenerator.getPrice())
                    .addLocation(new City(RandomGenerator.getCity(),
                            RandomGenerator.getCounty(),
                            RandomGenerator.getCountry()))
                    .addPeriod(RandomGenerator.getRange())
                    .addActivities(RandomGenerator.getActivities());
            placesC.insert2(places[i]);
        }
        for (Place p : places) {
            System.out.println(p);
        }
    }
}
