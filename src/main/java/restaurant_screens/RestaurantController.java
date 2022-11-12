package restaurant_screens;

import entities.OwnerUser;
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

    RestaurantResponseModel create(String owner, String name, String location,
                                   String cuisineType, int priceBucket, double avgStars, ArrayList<String> reviews) {
        RestaurantRequestModel requestModel = new RestaurantRequestModel(owner, name, location,
                cuisineType, priceBucket, avgStars, reviews);

        return userInput.create(requestModel);
    }
}
