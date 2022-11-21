package restaurant_screens;

import restaurant_use_case.RestaurantResponseModel;

// screen formatter interface

/**
 * The interface for the presenter so that interactors do not need to know which presenter they are using
 */
public interface RestaurantPresenter {
    RestaurantResponseModel prepareSuccessView(RestaurantResponseModel newRestaurant);

    RestaurantResponseModel prepareFailView(String error);
}
