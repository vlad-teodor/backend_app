import java.util.*;

/**
 * Container for the places
 */
public class PlaceContainer {
    private HashMap<String, Place> placesByName = new HashMap<>(); // name -> place
    private HashMap<String, CountryContainer> placesByCountry = new HashMap<>(); // country -> counties in the country
    ArrayList<Place> places = new ArrayList<>(); // list sorted ascending by price

    /**
     * Inserts the place into the placesByName map
     * @return true if it was inserted, false it already existed
     */
    boolean insert(Place place) {
        // place with the name already exists
        if (placesByName.containsKey(place.name)) return false;

        placesByName.put(place.name, place);
        return true;
    }

    /**
     * Inserts the place into its specific country container and the array that is sorted by price
     */
    void insert2(Place place) {
        CountryContainer countryContainer = placesByCountry.get(place.location.country);
        // create a new country container for the country if it doesn't exist
        if (countryContainer == null) {
            countryContainer = new CountryContainer();
            placesByCountry.put(place.location.country, countryContainer);
        }
        countryContainer.insert(place);

        Main.insertOrdered(places, place);
    }

    /**
     * @param name Name of the place requested
     * @return The requested place
     */
    Place getByName(String name) {
        return placesByName.get(name);
    }

    /**
     * @return a list of at most 5 places fullfilling the conditions
     */
    Place[] getByCost(String country, String county, String city, Date startDate, Date endDate) {
        if (placesByCountry.get(country) == null) {
            System.out.println("Country does not exist");
            return null;
        }
        return placesByCountry.get(country).getByCost(county, city, startDate, endDate);
    }
}

/**
 * Container for a country
 */
class CountryContainer {
    private HashMap<String, CountyContainer> counties = new HashMap<>(); // county name -> cities in the counties

    Place[] getByCost(String county, String city, Date startDate, Date endDate) {
        if (counties.get(county) == null) {
            System.out.println("County does not exist");
            return null;
        }
        return counties.get(county).getByCost(city, startDate, endDate);
    }

    /**
     * Inserts the place into its specific county container
     */
    void insert(Place place) {
        CountyContainer countyContainer = counties.get(place.location.county);
        if (countyContainer == null) {
            countyContainer = new CountyContainer();
            counties.put(place.location.county, countyContainer);
        }
        countyContainer.insert(place);
    }
}

/**
 * Container for a County
 */
class CountyContainer {
    private HashMap<String, CityContainer> cities = new HashMap<>(); // City name -> places in the city

    Place[] getByCost(String city, Date startDate, Date endDate) {
        if (cities.get(city) == null) {
            System.out.println("City does not exist");
            return null;
        }
        return cities.get(city).getByCost(startDate, endDate);
    }

    /**
     * Insert the place into its specific city container
     */
    void insert(Place place) {
        CityContainer cityContainer = cities.get(place.location.city);
        if (cityContainer == null) {
            cityContainer = new CityContainer();
            cities.put(place.location.city, cityContainer);
        }
        cityContainer.insert(place);
    }
}

/**
 * Container for a city, containing an array of the places it has
 */
class CityContainer {
    private ArrayList<Place> places = new ArrayList<>();

    /**
     * Insert the city into the list of places in the city, ordered by price, ascending
     */
    void insert(Place place) {
        Main.insertOrdered(places, place);
    }

    /**
     * Gets the top 5 cities by price
     */
    Place[] getByCost(Date startDate, Date endDate) {
        Place[] goodPlaces = new Place[5];
        int i = 0;
        for (Place place : places) {
            if (place.period.start.after(startDate) && place.period.end.before(endDate)) {
                goodPlaces[i++] = place;
            }
            if (i == 5) break; // at most 5 places
        }
        if (i == 0) return null;
        return goodPlaces;
    }
}
