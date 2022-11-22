package restaurant_use_case;

import java.io.IOException;

/**
 * The Restaurant Input Boundary interface which interactors that mutate but do not delete restaurants implement
 */
public interface RestaurantInputBoundary {
    RestaurantResponseModel create(RestaurantRequestModel requestModel);
}
