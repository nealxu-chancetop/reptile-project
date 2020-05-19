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

    @Field(name = "price")
    public String price;

    @Field(name = "rating")
    public Double rating;

    @Field(name = "location")
    public Location location;

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
}
