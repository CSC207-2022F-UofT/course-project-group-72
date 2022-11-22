package restaurant_use_case.screens;

// screen formatter interface

/**
 * The interface for the presenter so that interactors do not need to know which presenter they are using
 */
public interface RestaurantPresenter {
    RestaurantResponseModel prepareSuccessView(RestaurantResponseModel newRestaurant);

    RestaurantResponseModel prepareFailView(String error);
}
