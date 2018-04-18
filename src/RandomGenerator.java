import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class RandomGenerator {
    static Random r = new Random();
    static String[] cities = {"Bucharest", "Constanta", "Timisoara", "San Francisco", "Helsinki", "London", "Dublin",
            "Prague", "Vienna", "Paris", "Barcelona", "Rome", "Iasi", "Cluj"};
    static String[] counties = {"Ilfov", "Constanta", "Cluj", "Timis", "Iasi", "Bohemia", "California"};
    static String[] countries = {"Romania", "USA", "Finland", "United Kingdom", "Ireland", "Czehia", "Austria",
            "Spain", "France", "Italy"};
    static String[] activities = {"Hiking", "Biking", "Swimming", "Deltaplaning", "Skiing", "Sleeping",
            "Museums", "Clubbing", "Eating", "Praying", "Sauna"};

    static double getPrice() {
        return r.nextDouble() * 200;
    }

    static String getName() {
        int length = r.nextInt(10) + 5;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + r.nextInt(26)));
        }
        return sb.toString();
    }

    static String getCity() {
        return cities[r.nextInt(cities.length)];
    }

    static String getCounty() {
        return counties[r.nextInt(counties.length)];
    }

    static String getCountry() {
        return countries[r.nextInt(countries.length)];
    }

    static Range getRange() {
        Date startDate = new Date(new Date().getTime() + r.nextLong() % (5000000000L));
        Date endDate = new Date(startDate.getTime() + r.nextLong() % (5000000000L));
        return new Range(startDate, endDate);
    }

    static String[] getActivities() {
        int currentIndex = 0;
        int lastIndex = -1;
        LinkedList<String> list = new LinkedList<>();
        while (currentIndex < activities.length) {
            currentIndex += r.nextInt(activities.length - currentIndex);
            if (currentIndex == lastIndex) {
                currentIndex++;
                if (currentIndex >= activities.length) break;
            }
            list.add(activities[currentIndex]);
            lastIndex = currentIndex;
        }
        return list.toArray(new String[0]);
    }
}
