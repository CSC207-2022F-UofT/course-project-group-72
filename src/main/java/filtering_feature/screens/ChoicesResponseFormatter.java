package filtering_feature.screens;

import filtering_feature.interactors.RestaurantFilterFailed;

/**
 * The response formatter, formats the response to reflect the changes made
 */
public class ChoicesResponseFormatter implements ChoicesPresenter {

    /**
     *
     * @param responseModel the raw data passed by the interactor
     * @return the response, unmodified.
     */
    @Override
    public ChoicesResponseModel prepareSuccessView(ChoicesResponseModel responseModel){
        // Works as intended
        return responseModel;

    }


    /**
     * Allows for a dialogue box that is shown when the user encounters an error
     *
     * @param error The error that occurred during user selection
     * @return The error that occurred
     */
    @Override
    public ChoicesResponseModel prepareFailView(String error) {
        // Throws error for empty sorted list (no restaurants match selection)
        throw new RestaurantFilterFailed(error);
    }


}