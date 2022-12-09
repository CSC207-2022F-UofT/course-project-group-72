package restaurant_feature.screens;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_feature.interactors.RestaurantEditRequestModel;
import restaurant_feature.interfaces.RestaurantInputBoundary;

public class RestaurantEditController {
    /**
     * The modification interactor
     */
    final RestaurantInputBoundary userInput;

    /**
     *
     * @param inputData the desired interactor to be used
     */
    public RestaurantEditController(RestaurantInputBoundary inputData) {
        this.userInput = inputData;
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
