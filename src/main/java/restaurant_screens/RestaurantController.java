package restaurant_screens;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.RestaurantEditRequestModel;
import restaurant_use_case.RestaurantInputBoundary;
import restaurant_use_case.RestaurantRequestModel;
import restaurant_use_case.RestaurantResponseModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Restaurant Controller for modification, but not deletion interactors
 */
public class RestaurantController {
    /**
     * The modification interactor
     */
    final RestaurantInputBoundary userInput;

    /**
     *
     * @param inputData the desired interactor to be used
     */
    public RestaurantController(RestaurantInputBoundary inputData) {
        this.userInput = inputData;
    }

    /**
     *  Used for creating a new restaurant
     *
     * @param owner the current active owner
     * @param name the name of the new Restaurant
     * @param location the unique location of the new Restaurant
     * @param cuisineType the cuisine served at the new Restaurant
     * @param priceBucket the price range of the new Restaurant
     * @return the result of the interaction
     */
    RestaurantResponseModel create(OwnerUser owner, String name, String location, String cuisineType, int priceBucket) {
        RestaurantRequestModel requestModel = new RestaurantRequestModel(owner, name, location,
                cuisineType, priceBucket);

        return userInput.create(requestModel);
    }

    /**
     *
     * @param owner the current active owner
     * @param name the new name of the Restaurant
     * @param location the unique identifier of the Restaurant
     * @param cuisineType the new cuisine served at the Restaurant
     * @param priceBucket the new price range of the Restaurant
     * @param restaurant the old version of the Restaurant
     * @return the result of the interaction
     */
    RestaurantResponseModel edit(OwnerUser owner, String name, String location,
                                   String cuisineType, int priceBucket, Restaurant restaurant) {
        RestaurantEditRequestModel requestModel = new RestaurantEditRequestModel(owner, name, location,
                cuisineType, priceBucket, restaurant);

        return userInput.create(requestModel);
    }
}
