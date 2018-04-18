/**
 * A class containing the details of a city (name, county and country)
 */
public class City {
    String city, county, country;

    City(String city, String county, String country) {
        this.city = city;
        this.county = county;
        this.country = country;
    }

    @Override
    public String toString() {
        return this.city + "/" + this.county + "/" + this.country;
    }
}
