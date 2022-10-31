package entities;

public interface RestaurantFactory {
    Restaurant create(OwnerUser owner, String name, String location, String cuisineType, int priceBucket);
}
