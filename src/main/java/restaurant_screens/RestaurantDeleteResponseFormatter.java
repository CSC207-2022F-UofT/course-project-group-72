package restaurant_screens;

import restaurant_use_case.RestaurantResponseModel;

public class RestaurantDeleteResponseFormatter implements RestaurantDeletePresenter{
    @Override
    public RestaurantResponseModel prepareSuccessView(String message, String restaurant, String deletionTime) {
        return new RestaurantResponseModel(null, "Your restaurant: " + restaurant
                + " was successfully " + message, deletionTime);
    }

    @Override
    public RestaurantResponseModel prepareFailView(String error) {
        throw new RestaurantInteractionFailed(error);
    }
}
