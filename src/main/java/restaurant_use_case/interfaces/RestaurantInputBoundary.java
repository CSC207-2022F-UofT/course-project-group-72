package restaurant_use_case.interfaces;

import restaurant_use_case.screens.RestaurantResponseModel;
import restaurant_use_case.interactors.RestaurantRequestModel;

/**
 * The Restaurant Input Boundary interface which interactors that mutate but do not delete restaurants implement
 */
public interface RestaurantInputBoundary {
    RestaurantResponseModel create(RestaurantRequestModel requestModel);
}
