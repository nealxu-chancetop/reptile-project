package service;

import com.mongodb.client.model.Sorts;
import core.framework.http.ContentType;
import core.framework.http.HTTPClient;
import core.framework.http.HTTPMethod;
import core.framework.http.HTTPRequest;
import core.framework.http.HTTPResponse;
import core.framework.inject.Inject;
import core.framework.json.JSON;
import core.framework.mongo.MongoCollection;
import core.framework.mongo.Query;
import core.framework.util.Lists;
import core.framework.util.Maps;
import domain.Restaurant;
import domain.SearchBusinessResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Neal
 */
public class ReptileService {
    private final HTTPClient httpClient;
    @Inject
    MongoCollection<Restaurant> restaurantCollection;

    public ReptileService(HTTPClient httpClient) {
        this.httpClient = httpClient;
    }

    public void fetchRestaurant() {
        List<String> counties = List.of("Westfield", "BerkeleyHeights"
            , "Mountainside", "NewProvidence", "Summit",
            "Cranford", "Fanwood", "Scotchplains", "Springfield", "Kenilworth", "Clark",
            "UnionTownship", "Garwood", "Linden", "RosellePark", "Rahway", "Hillside",
            "Plainfield", "Winfield", "Elizabeth", "Roselle"
        );
        Map<String, Restaurant> restaurantMap = Maps.newHashMap();
        for (String county : counties) {
            int offset = 0;
            while (true) {
                String url = String.format("https://api.yelp.com/v3/businesses/search?term=restaurants&location=%s&limit=50&offset=%d", county, offset);
                var request = new HTTPRequest(HTTPMethod.GET, url);
                request.headers.put("Authorization", "Bearer 6yj6cRiLPUhzO0sBVb5UDI2M4IEHOVhBJCwsCJ4vEJaHGBLlyHPEI0lf3OppXTOxgnw9wHq4n89CqE76ImPRDFjkV1gljUm-1ory_fRNfQdTjcdbEf9QvKABswTUXXYx");
                request.accept(ContentType.APPLICATION_JSON);
                HTTPResponse response = httpClient.execute(request);
                SearchBusinessResponse searchBusinessResponse = JSON.fromJSON(SearchBusinessResponse.class, new String(response.body, StandardCharsets.UTF_8));
                searchBusinessResponse.businesses.forEach(restaurantView -> {
                    Restaurant restaurant = restaurantMap.get(restaurantView.id);
                    if (restaurant == null) {
                        Restaurant newRestaurant = buildRestaurant(restaurantView);
                        newRestaurant.counties.add(county);
                        restaurantMap.put(restaurantView.id, newRestaurant);
                    } else {
                        restaurant.counties.add(county);
                        restaurant.counties = restaurant.counties.stream().distinct().collect(Collectors.toList());
                    }
                });
                if (searchBusinessResponse.businesses.size() != 50) {
                    break;
                } else {
                    offset += 50;
                }
                if (offset >= 1000) break;
            }
        }
        restaurantCollection.bulkReplace(new ArrayList<>(restaurantMap.values()));
    }

    private Restaurant buildRestaurant(SearchBusinessResponse.Restaurant restaurantView) {
        Restaurant restaurant = new Restaurant();
        restaurant.id = restaurantView.id;
        restaurant.alias = restaurantView.alias;
        restaurant.name = restaurantView.name;
        restaurant.price = restaurantView.price;
        restaurant.rating = restaurantView.rating;
        if (restaurantView.location != null) {
            Restaurant.Location location = new Restaurant.Location();
            location.address1 = restaurantView.location.address1;
            location.address2 = restaurantView.location.address2;
            location.address3 = restaurantView.location.address3;
            location.city = restaurantView.location.city;
            location.zipCode = restaurantView.location.zipCode;
            location.country = restaurantView.location.country;
            location.state = restaurantView.location.state;
            restaurant.location = location;
        }
        restaurant.counties = Lists.newArrayList();
        return restaurant;
    }

    public void fetchMenu() {
        int skip = 0;
        while (true) {
            Query query = new Query();
            query.limit = 50;
            query.skip = skip;
            query.sort = Sorts.ascending("_id");
            List<Restaurant> restaurants = restaurantCollection.find(query);
            for (Restaurant restaurant : restaurants) {
                String url = String.format("https://www.yelp.com/menu/%s", restaurant.alias);
                var request = new HTTPRequest(HTTPMethod.GET, url);
                HTTPResponse response = httpClient.execute(request);
                Document document = Jsoup.parse(new String(response.body, StandardCharsets.UTF_8));


                Element menuElement = document.selectFirst("div[class=menu-sections]");
                if (menuElement == null) {
                    restaurant.status = "NOT_MENU";
                    continue;
                }
                Elements elements = menuElement.children();
                Restaurant.Menu menu = new Restaurant.Menu();
                List<Restaurant.Category> categories = Lists.newArrayList();

                Restaurant.Category category = null;
                for (int i = 0; i < elements.size(); i++) {
                    var element = elements.get(i);
                    if (i == 0) {
                        Element descriptionElement = element.selectFirst("p[class=menu-text]");
                        if (descriptionElement != null) {
                            menu.description = descriptionElement.text();
                            continue;
                        }
                    }
                    if (element.classNames().contains("section-header")) {
                        category = new Restaurant.Category();
                        category.name = element.selectFirst("h2[class=alternate]").text();
                        Element descriptionElement = element.selectFirst("p[class=menu-section-description]");
                        if (descriptionElement != null)
                            category.description = descriptionElement.text();
                        continue;
                    }
                    if (element.classNames().contains("u-space-b3")) {
                        Elements childrenElements = element.children();
                        category.menuItems = childrenElements.stream().map(childrenElement -> {
                            Restaurant.MenuItem menuItem = new Restaurant.MenuItem();
                            menuItem.name = childrenElement.selectFirst("h4").text();
                            Element descriptionElement = childrenElement.selectFirst("p[class=menu-item-details-description]");
                            if (descriptionElement != null)
                                menuItem.description = descriptionElement.text();
                            menuItem.price = childrenElement.selectFirst("div[class=menu-item-prices arrange_unit]").text();
                            return menuItem;
                        }).collect(Collectors.toList());
                        categories.add(category);
                    }
                }
                menu.categories = categories;
                restaurant.menu = menu;
                restaurant.status = "OK";
            }
            restaurantCollection.bulkReplace(restaurants);
            skip += 50;
            if (restaurants.size() != 50) break;
        }
    }
}