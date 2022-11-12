package restaurant_use_case;

import entities.Restaurant;

import java.util.ArrayList;

public interface ChoicesDSGateway {
    ArrayList<Restaurant> retrieveAllRestaurants();
}
