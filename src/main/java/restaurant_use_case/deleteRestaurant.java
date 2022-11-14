package restaurant_use_case;

import restaurant_screens.RestaurantDeletePresenter;

import java.time.LocalDateTime;

public class deleteRestaurant implements RestaurantDeleteInputBoundary{
    private final RestaurantDSGateway gateway;
    private final RestaurantDeletePresenter presenter;

    public deleteRestaurant(RestaurantDSGateway dataGateway, RestaurantDeletePresenter presenter) {
        gateway = dataGateway;
        this.presenter = presenter;
    }

    @Override
    public RestaurantResponseModel delete(String location) {
        if (!gateway.existsByLocation(location)) {
            // Checks if the restaurant is already in the system and can be edited
            return presenter.prepareFailView("RESTAURANT DOES NOT EXIST");
        }

        LocalDateTime now = LocalDateTime.now();
        //reinitializing restaurant here is necessary may be changed later
        String restaurant = gateway.retrieveRestaurant(location).getName();
        gateway.deleteRestaurant(location);

        return presenter.prepareSuccessView("deleted", restaurant, now.toString());

    }
}
