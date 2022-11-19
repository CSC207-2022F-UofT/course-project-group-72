package restaurant_screens;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.RestaurantDeleteRequestModel;
import restaurant_use_case.RestaurantResponseModel;
import restaurant_use_case.RestaurantDeleteInputBoundary;

public class RestaurantDeleteController {
    private final RestaurantDeleteInputBoundary interactor;

    public RestaurantDeleteController(RestaurantDeleteInputBoundary interactor) {
        this.interactor = interactor;
    }

    public RestaurantResponseModel delete(OwnerUser owner, Restaurant restaurant) {
        RestaurantDeleteRequestModel requestModel = new RestaurantDeleteRequestModel(owner, restaurant);

        return interactor.delete(requestModel);
    }
}
