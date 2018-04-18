import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static java.lang.Math.abs;

class RandomGenerator {
    private static Random r = new Random();
    private static String[] cities = {"Bucharest", "Constanta", "Timisoara", "San Francisco", "Helsinki", "London", "Dublin",
            "Prague", "Vienna", "Paris", "Barcelona", "Rome", "Iasi", "Cluj"};
    private static String[] counties = {"Ilfov", "Constanta", "Cluj", "Timis", "Iasi", "Bohemia", "California"};
    private static String[] countries = {"Romania", "USA", "Finland", "United Kingdom", "Ireland", "Czehia", "Austria",
            "Spain", "France", "Italy"};
    private static String[] activities = {"Hiking", "Biking", "Swimming", "Deltaplaning", "Skiing", "Sleeping",
            "Museums", "Clubbing", "Eating", "Praying", "Sauna"};

    static double getPrice() {
        return r.nextDouble() * 200;
    }

    /**
     * @return a random string name
     */
    static String getName() {
        int length = r.nextInt(10) + 5; // [5, 15) characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + r.nextInt(26))); // append 'a' -> 'z'
        }
        return sb.toString();
    }

    /**
     * @return a random city
     */
    static String getCity() {
        return cities[r.nextInt(cities.length)];
    }

    /**
     * @return a random county
     */
    static String getCounty() {
        return counties[r.nextInt(counties.length)];
    }

    /**
     * @return a random country
     */
    static String getCountry() {
        return countries[r.nextInt(countries.length)];
    }

    /**
     * @return a Range of 2 dates, maximum 2 months apart.
     */
    static Range getRange() {
        Date startDate = new Date(new Date().getTime() + abs(r.nextLong() % (5000000000L)));
        Date endDate = new Date(startDate.getTime() + abs(r.nextLong() % (5000000000L)));
        return new Range(startDate, endDate);
    }

    /**
     * @return a list of activities
     */
    static ArrayList<String> getActivities() {
        int currentIndex = 0; // current index in the activities list
        int lastIndex = -1; // the index after the last iteration
        ArrayList<String> list = new ArrayList<>();

        while (currentIndex < activities.length) {
            // move in the activities list
            currentIndex += r.nextInt(activities.length - currentIndex + 1);
            if (currentIndex == lastIndex || currentIndex >= activities.length) {
                // don't allow duplicates or random endless loops
                currentIndex++;
                if (currentIndex >= activities.length) break;
            }
            list.add(activities[currentIndex]);
            lastIndex = currentIndex; // saves the last index
        }

        return list;
    }
}
