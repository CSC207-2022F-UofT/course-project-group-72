package entities;

public class RestaurantFactory {
    Restaurant create(OwnerUser owner, String name, String location, String cuisineType, int priceBucket) {
        return new Restaurant(owner, name, location, cuisineType, priceBucket);
    }
}
