package domain;

import core.framework.api.json.Property;

import java.util.List;

/**
 * @author Neal
 */
public class SearchBusinessResponse {
    @Property(name = "businesses")
    public List<Restaurant> businesses;

    @Property(name = "total")
    public Long total;

    public static class Restaurant {
        @Property(name = "id")
        public String id;

        @Property(name = "alias")
        public String alias;

        @Property(name = "name")
        public String name;

        @Property(name = "price")
        public String price;

        @Property(name = "rating")
        public Double rating;

        @Property(name = "location")
        public Location location;
    }

    public static class Location {
        @Property(name = "address1")
        public String address1;

        @Property(name = "address2")
        public String address2;

        @Property(name = "address3")
        public String address3;

        @Property(name = "city")
        public String city;

        @Property(name = "zip_code")
        public String zipCode;

        @Property(name = "country")
        public String country;

        @Property(name = "state")
        public String state;
    }
}
