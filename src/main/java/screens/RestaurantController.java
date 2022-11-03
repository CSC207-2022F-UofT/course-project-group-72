package screens;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.RestaurantInputBoundary;
import restaurant_use_case.RestaurantRequestModel;
import restaurant_use_case.RestaurantResponseModel;

public class RestaurantController {

    final RestaurantInputBoundary userInput;

    public RestaurantController(RestaurantInputBoundary inputData) {
        this.userInput = inputData;
    }

    RestaurantResponseModel create(OwnerUser owner, String name, String location,
                                   String cuisineType, int priceBucket) {
        RestaurantRequestModel requestModel = new RestaurantRequestModel(owner, name, location,
                cuisineType, priceBucket);

        return userInput.create(requestModel);
    }
}
