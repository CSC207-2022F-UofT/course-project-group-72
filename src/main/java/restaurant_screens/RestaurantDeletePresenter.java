package restaurant_screens;

import restaurant_use_case.RestaurantResponseModel;

public interface RestaurantDeletePresenter {

    RestaurantResponseModel prepareSuccessView(String message, String restaurant, String deletionTime);

    RestaurantResponseModel prepareFailView(String error);
}

