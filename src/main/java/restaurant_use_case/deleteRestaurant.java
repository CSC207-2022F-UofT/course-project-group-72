package restaurant_use_case;

import restaurant_screens.RestaurantDeletePresenter;

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
            return presenter.prepareFailView("RESTAURANT DOES NOT EXIST");
        }

        //reinitializing restaurant here is necessary may be changed later
        String restaurant = gateway.retrieveRestaurant(location).getName();
        gateway.deleteRestaurant(location);

        return presenter.prepareSuccessView("deleted", restaurant);

    }
}
