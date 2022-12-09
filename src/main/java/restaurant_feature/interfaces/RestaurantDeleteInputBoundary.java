package restaurant_feature.interfaces;

import restaurant_feature.screens.RestaurantResponseModel;
import restaurant_feature.interactors.RestaurantDeleteRequestModel;

/**
 * The Input boundary that the Delete Interactor implements, for dependency inversion
 */
public interface RestaurantDeleteInputBoundary {
    /**
     *
     * @param requestModel the RestaurantDeleteRequestModel, containing the owner and the Restaurant impacted
     * @return a response model which contains the deleted Restaurant and information for the screen
     */
    RestaurantResponseModel delete(RestaurantDeleteRequestModel requestModel);
}
