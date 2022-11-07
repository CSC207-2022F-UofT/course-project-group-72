package restaurant_screens;

import restaurant_use_case.RestaurantResponseModel;

public class RestaurantResponseFormatter implements RestaurantPresenter{

    @Override
    public RestaurantResponseModel prepareSuccessView(RestaurantResponseModel unformatted) {
        unformatted.setOperation("Successfully " + unformatted.getOperation() + " " +
                unformatted.getRestaurant().getName());
        return unformatted;
    }

    @Override
    public RestaurantResponseModel prepareFailView(String error) {
        throw new RestaurantCreationFailed(error);
    }
}
