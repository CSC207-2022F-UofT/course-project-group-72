package restaurant_use_case.screens;

/**
 * The presenter interface so that interactor can call on the presenter without knowing what it does
 */
public interface RestaurantDeletePresenter {

    RestaurantResponseModel prepareSuccessView(String message, String restaurant, String deletionTime);

    RestaurantResponseModel prepareFailView(String error);
}

