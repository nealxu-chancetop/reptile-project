package domain;

import core.framework.mongo.Collection;
import core.framework.mongo.Field;
import core.framework.mongo.Id;

import java.util.List;

/**
 * @author Neal
 */
@Collection(name = "restaurants")
public class Restaurant {
    @Id
    public String id;

    @Field(name = "alias")
    public String alias;

    @Field(name = "name")
    public String name;

    @Field(name = "image_url")
    public String imageUrl;

    @Field(name = "is_closed")
    public Boolean isClosed;

    @Field(name = "url")
    public String url;

    @Field(name = "review_count")
    public Integer reviewCount;

    @Field(name = "cusines")
    public List<Cuisine> cuisines;

    @Field(name = "rating")
    public Double rating;

    @Field(name = "coordinates")
    public Coordinates coordinates;

    @Field(name = "transactions")
    public List<String> transactions;

    @Field(name = "price")
    public String price;

    @Field(name = "location")
    public Location location;

    @Field(name = "phone")
    public String phone;

    @Field(name = "display_phone")
    public String displayPhone;

    @Field(name = "distance")
    public Double distance;
    
    @Field(name = "counties")
    public List<String> counties;

    @Field(name = "menu")
    public Menu menu;

    @Field(name = "status")
    public String status;

    public static class Location {
        @Field(name = "address1")
        public String address1;

        @Field(name = "address2")
        public String address2;

        @Field(name = "address3")
        public String address3;

        @Field(name = "city")
        public String city;

        @Field(name = "zip_code")
        public String zipCode;

        @Field(name = "country")
        public String country;

        @Field(name = "state")
        public String state;

        @Field(name = "display_address")
        public List<String> displayAddress;
    }

    public static class Menu {
        @Field(name = "description")
        public String description;

        @Field(name = "categories")
        public List<Category> categories;
    }

    public static class Category {
        @Field(name = "description")
        public String description;

        @Field(name = "name")
        public String name;

        @Field(name = "menu_items")
        public List<MenuItem> menuItems;
    }

    public static class MenuItem {
        @Field(name = "name")
        public String name;

        @Field(name = "description")
        public String description;

        @Field(name = "price")
        public String price;
    }


    public static class Cuisine {
        @Field(name = "alias")
        public String alias;

        @Field(name = "title")
        public String title;
    }

    public static class Coordinates {
        @Field(name = "latitude")
        public String latitude;

        @Field(name = "longitude")
        public String longitude;
    }
}
