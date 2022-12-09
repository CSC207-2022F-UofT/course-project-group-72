package restaurant_feature.screens;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_feature.interactors.RestaurantDeleteRequestModel;
import restaurant_feature.interfaces.RestaurantDeleteInputBoundary;

/**
 * The Controller for the deletion interaction
 */
public class RestaurantDeleteController {
    /**
     * The removal interactor
     */
    private final RestaurantDeleteInputBoundary interactor;

    /**
     *
     * @param interactor the desired interactor to be used
     */
    public RestaurantDeleteController(RestaurantDeleteInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     *
     * @param owner current active owner
     * @param restaurant the restaurant to be deleted
     * @return the result of the interaction
     */
    public RestaurantResponseModel delete(OwnerUser owner, Restaurant restaurant) {
        RestaurantDeleteRequestModel requestModel = new RestaurantDeleteRequestModel(owner, restaurant);

        return interactor.delete(requestModel);
    }
}
