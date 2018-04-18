import java.util.Random;

public class Loc {
    String name, county, country;
    public Loc(String name, String county, String country) {
        this.name= name;
        this.county = county;
        this.country = country;
    }

    @Override
    public String toString() {
        return this.name + "/" + this.county + "/" + this.country;
    }
}
