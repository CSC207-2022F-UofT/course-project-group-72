package restaurant_use_case;

import java.io.IOException;

public interface RestaurantInputBoundary {
    RestaurantResponseModel create(RestaurantRequestModel requestModel);
}
