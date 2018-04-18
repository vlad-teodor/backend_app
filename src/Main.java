import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    /**
     * Inserts the place into the places list, ordered by daily price
     */
    static void insertOrdered(ArrayList<Place> places, Place place) {
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).dailyPrice > place.dailyPrice) {
                places.add(i, place);
                return;
            }
        }
        places.add(place);
    }

    /**
     * Parses the date from the given scanner
     */
    private static Date parseDate(Scanner s) throws ParseException {
        System.out.print("Day:");
        System.out.flush();
        int day = Integer.parseInt(s.nextLine());

        System.out.print("Month:");
        System.out.flush();
        int month = Integer.parseInt(s.nextLine());

        System.out.print("Year:");
        System.out.flush();
        int year = Integer.parseInt(s.nextLine());

        SimpleDateFormat parser = new SimpleDateFormat("zzzz YYYY MM DD");
        return parser.parse("EEST " + year + " " + month + " " + day);
    }

    public static void main(String[] args) throws ParseException {
        PlaceContainer places = new PlaceContainer();
        int PLACES = 10000;
        for (int i = 0; i < PLACES; i++) {
            Place newPlace;
            do { // create a new place with the name without allowing duplicates
                newPlace = new Place(RandomGenerator.getName());
            } while (!places.insert(newPlace));

            //inserts the rest of the data into the place, randomly
            newPlace = newPlace.addPrice(RandomGenerator.getPrice())
                    .addLocation(new City(RandomGenerator.getCity(),
                            RandomGenerator.getCounty(),
                            RandomGenerator.getCountry()))
                    .addPeriod(RandomGenerator.getRange())
                    .addActivities(RandomGenerator.getActivities());
            places.insert2(newPlace);

            // There are saunas everywhere in Finland
            if ((newPlace.location.city.equals("Helsinki") || newPlace.location.country.equals("Finland")) &&
                    !newPlace.activities.contains("Sauna")) newPlace.activities.add("Sauna");
        }
        for (Place p : places.places) {
            System.out.println(p);
        }

        boolean running = true;
        Scanner s = new Scanner(System.in);
        while (running) {
            System.out.println("Operations:\n" +
                    "1. Info about location\n" +
                    "2. top 5 locations\n" +
                    "3. Cheapest place for 10 days\n" +
                    "0. Exit");
            System.out.print("Op:");
            int command = Integer.parseInt(s.nextLine());
            switch (command) {
                case 0:
                    running = false;
                    break;
                case 1: // get info about location
                    System.out.print("Place name:");
                    System.out.println(places.getByName(s.nextLine()));
                    break;
                case 2: // get top 5 from country/county/city and date
                    System.out.print("Country:");
                    System.out.flush();
                    String country = s.nextLine();

                    System.out.print("County:");
                    System.out.flush();
                    String county = s.nextLine();

                    System.out.print("City:");
                    System.out.flush();
                    String city = s.nextLine();

                    System.out.println("Start Date:");
                    Date start = parseDate(s);
                    System.out.println(start);

                    System.out.println("End Date:");
                    Date end = parseDate(s);
                    System.out.println(end);

                    Place[] result = places.getByCost(country, county, city, start, end);
                    if (result == null) {
                        System.out.println("No results found");
                        break;
                    }
                    for (Place place : result) {
                        System.out.println(place);
                    }
                    break;
                case 3: // get cheapest 10 places
                    System.out.print("Activity:");
                    System.out.flush();
                    String activity = s.nextLine();

                    boolean found = false;
                    for (Place place : places.places) {
                        if (place.activities.contains(activity)) {
                            System.out.println(place);
                            found = true; // a place was found
                            break;
                        }
                    }
                    if (!found) System.out.println("No results found");
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }

        }
    }
}
