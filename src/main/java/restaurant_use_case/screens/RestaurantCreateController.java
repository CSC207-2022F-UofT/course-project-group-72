package restaurant_use_case.screens;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.interactors.RestaurantEditRequestModel;
import restaurant_use_case.interfaces.RestaurantInputBoundary;
import restaurant_use_case.interactors.RestaurantRequestModel;

/**
 * The Restaurant Controller for modification, but not deletion interactors
 */
public class RestaurantCreateController {
    /**
     * The modification interactor
     */
    final RestaurantInputBoundary userInput;

    /**
     *
     * @param inputData the desired interactor to be used
     */
    public RestaurantCreateController(RestaurantInputBoundary inputData) {
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
}
