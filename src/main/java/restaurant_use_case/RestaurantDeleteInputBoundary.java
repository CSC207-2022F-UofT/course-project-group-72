package restaurant_use_case;

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
