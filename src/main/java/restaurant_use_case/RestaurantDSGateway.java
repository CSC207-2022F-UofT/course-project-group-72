package restaurant_use_case;

import entities.Restaurant;

import java.util.ArrayList;

public interface RestaurantDSGateway {
    void save(RestaurantDSRequestModel requestModel);
    boolean existsByLocation(String location);
    Restaurant retrieveRestaurant(String location);
    ArrayList<Restaurant> retrieveAllRestaurants();
    void delete(String location);
}
