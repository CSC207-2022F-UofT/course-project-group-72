package restaurant_feature.screens;

import global.ViewInterface;

/**
 * The responseFormatter/ presenter which updated the screen
 */
public class RestaurantDeleteResponseFormatter implements RestaurantDeletePresenter{
    private final ViewInterface previousView;
    public RestaurantDeleteResponseFormatter(ViewInterface previousView) {
        this.previousView = previousView;
    }
    /**
     *
     * @param message the message to be displayed
     * @param restaurant the restaurant that was changed
     * @param deletionTime the time that the change was made (may be removed)
     * @return a formatted version of the response model that is used by a text box
     */
    @Override
    public RestaurantResponseModel prepareSuccessView(String message, String restaurant, String deletionTime) {
        this.previousView.back();
        return new RestaurantResponseModel(restaurant, deletionTime, "Your restaurant: " + restaurant
                + " was successfully " + message);
    }

    /**
     * Will allow for a text box to display the error
     *
     * @param error the error that occured
     * @return the error that occured
     */
    @Override
    public RestaurantResponseModel prepareFailView(String error) {
        throw new RestaurantInteractionFailed(error);
    }
}
