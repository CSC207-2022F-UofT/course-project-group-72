package restaurant_screens;

import restaurant_use_case.RestaurantResponseModel;

// screen formatter interface

public interface RestaurantPresenter {
    RestaurantResponseModel prepareSuccessView(RestaurantResponseModel newRestaurant);

    RestaurantResponseModel prepareFailView(String error);
}
