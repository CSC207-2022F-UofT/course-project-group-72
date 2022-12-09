package restaurant_feature.interfaces;

import restaurant_feature.screens.RestaurantResponseModel;
import restaurant_feature.interactors.RestaurantRequestModel;

/**
 * The Restaurant Input Boundary interface which interactors that mutate but do not delete restaurants implement
 */
public interface RestaurantInputBoundary {
    RestaurantResponseModel create(RestaurantRequestModel requestModel);
}
