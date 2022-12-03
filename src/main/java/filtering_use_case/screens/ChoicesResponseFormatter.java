package filtering_use_case.screens;

import filtering_use_case.interactors.RestaurantFilterFailed;

public class ChoicesResponseFormatter implements ChoicesPresenter {

    @Override
    public ChoicesResponseModel prepareSuccessView(ChoicesResponseModel responseModel){
        // Works as intended
        return responseModel;

    }

    @Override
    public ChoicesResponseModel prepareFailView(String error) {
        // Throws error for empty sorted list (no restaurants match selection)
        throw new RestaurantFilterFailed(error);
    }


}