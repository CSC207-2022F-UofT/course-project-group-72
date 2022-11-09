package restaurant_screens;

import restaurant_use_case.RestaurantResponseModel;
import restaurant_use_case.RestaurantDeleteInputBoundary;

public class RestaurantDeleteController {
    private final RestaurantDeleteInputBoundary interactor;

    public RestaurantDeleteController(RestaurantDeleteInputBoundary interactor) {
        this.interactor = interactor;
    }

    public RestaurantResponseModel delete(String location) {
        return interactor.delete(location);
    }
}
