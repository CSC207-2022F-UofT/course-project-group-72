package restaurant_feature.screens;

import global.ViewInterface;

/**
 * The response formatter, formats the response and refreshes the previous frame to reflect the changes made
 */
public class RestaurantResponseFormatter implements RestaurantPresenter{
    /**
     * The previous frame
     */
    private final ViewInterface previousView;

    public RestaurantResponseFormatter(ViewInterface previousView) {
        this.previousView = previousView;
    }

    /**
     * Allows for a text box to be displayed and refreshes the previous screen
     *
     * @param unformatted the raw data passed by the interactor
     * @return a formatted version of the response
     */
    @Override
    public RestaurantResponseModel prepareSuccessView(RestaurantResponseModel unformatted) {
        unformatted.setOperation("Successfully " + unformatted.getOperation() + " " +
                unformatted.getRestaurant());
        previousView.refresh();
        return unformatted;
    }

    /**
     *
     * @param error the error that occurred
     * @return the error that occurred
     */
    @Override
    public RestaurantResponseModel prepareFailView(String error) {
        throw new RestaurantInteractionFailed(error);
    }
}
