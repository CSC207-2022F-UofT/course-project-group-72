package restaurant_screens;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.RestaurantEditRequestModel;
import restaurant_use_case.RestaurantInputBoundary;
import restaurant_use_case.RestaurantRequestModel;
import restaurant_use_case.RestaurantResponseModel;

import java.io.IOException;
import java.util.ArrayList;

public class RestaurantController {

    final RestaurantInputBoundary userInput;

    public RestaurantController(RestaurantInputBoundary inputData) {
        this.userInput = inputData;
    }

    RestaurantResponseModel create(String owner, String name, String location, String cuisineType, int priceBucket) {
        RestaurantRequestModel requestModel = new RestaurantRequestModel(owner, name, location,
                cuisineType, priceBucket);

        return userInput.create(requestModel);
    }

    RestaurantResponseModel edit(String owner, String name, String location,
                                   String cuisineType, int priceBucket, Restaurant restaurant) {
        RestaurantEditRequestModel requestModel = new RestaurantEditRequestModel(owner, name, location,
                cuisineType, priceBucket, restaurant);

        return userInput.create(requestModel);
    }
}
