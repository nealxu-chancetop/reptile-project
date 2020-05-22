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

        @Property(name = "image_url")
        public String imageUrl;

        @Property(name = "is_closed")
        public Boolean isClosed;

        @Property(name = "url")
        public String url;

        @Property(name = "review_count")
        public Integer reviewCount;

        @Property(name = "categories")
        public List<Category> categories;

        @Property(name = "rating")
        public Double rating;

        @Property(name = "coordinates")
        public Coordinates coordinates;

        @Property(name = "transactions")
        public List<String> transactions;

        @Property(name = "price")
        public String price;

        @Property(name = "location")
        public Location location;

        @Property(name = "phone")
        public String phone;

        @Property(name = "display_phone")
        public String displayPhone;

        @Property(name = "distance")
        public Double distance;
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

        @Property(name = "display_address")
        public List<String> displayAddress;
    }

    public static class Category {
        @Property(name = "alias")
        public String alias;

        @Property(name = "title")
        public String title;
    }

    public static class Coordinates {
        @Property(name = "latitude")
        public String latitude;

        @Property(name = "longitude")
        public String longitude;
    }
}
