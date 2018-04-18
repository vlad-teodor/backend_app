import java.util.*;

public class PlaceContainer {
    private HashMap<String, Place> placesByName = new HashMap<>();
    private HashMap<String, CountryContainer> placesByCountry = new HashMap<>();

    /**
     * Inserts the place into the placesByName map
     * @return true if it was inserted, false it already existed
     */
    boolean insert(Place place) {
        if (placesByName.containsKey(place.name)) return false;

        placesByName.put(place.name, place);
        return true;
    }

    /**
     * Inserts the place into its specific country container
     */
    void insert2(Place place) {
        CountryContainer countryContainer = placesByCountry.get(place.location.country);
        // create a new country container for the country if it doesn't exist
        if (countryContainer == null) {
            countryContainer = new CountryContainer();
            placesByCountry.put(place.location.country, countryContainer);
        }
        countryContainer.insert(place);
    }

    Place getByName(String name) {
        return placesByName.get(name);
    }

    Place[] getByCost(String country, String county, String city, Date startDate, Date endDate) {
        return placesByCountry.get(country).getByCost(county, city, startDate, endDate);
    }
}

class CountryContainer {
    private HashMap<String, CountyContainer> counties = new HashMap<>();

    Place[] getByCost(String county, String city, Date startDate, Date endDate) {
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

class CountyContainer {
    private HashMap<String, CityContainer> cities = new HashMap<>();

    Place[] getByCost(String city, Date startDate, Date endDate) {
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

class CityContainer {
    private ArrayList<Place> places = new ArrayList<>();

    /**
     * Insert the city into the list of places in the city, ordered by price, ascending
     * Does this by inserting before the lowest priced place with a price higher than the place's price
     */
    void insert(Place place) {
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).dailyPrice > place.dailyPrice) {
                places.add(i, place);
                return;
            }
        }
        places.add(place);
    }

    /**
     * Gets the top 5 cities by price
     */
    Place[] getByCost(Date startDate, Date endDate) {
        Place[] goodPlaces = new Place[5];
        int i = 0;
        for (Place place : places) {
            if (place.period.start.before(startDate) && place.period.end.after(endDate)) {
                goodPlaces[i++] = place;
            }
            if (i == 5) break;
        }
        return goodPlaces;
    }
}
