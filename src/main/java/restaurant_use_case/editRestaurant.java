package restaurant_use_case;

import entities.RestaurantFactory;
import restaurant_screens.RestaurantPresenter;

public class editRestaurant implements RestaurantInputBoundary{
    private final RestaurantDSGateway gateway;
    private final RestaurantPresenter presenter;

    public editRestaurant(RestaurantDSGateway dataGateway,
                          RestaurantPresenter presenter) {
        gateway = dataGateway;
        this.presenter = presenter;
    }

    @Override
    public RestaurantResponseModel create(RestaurantRequestModel requestModel) {
        if (!gateway.existsByLocation(requestModel.getName())) {
            return presenter.prepareFailView("RESTAURANT DOES NOT EXIST");
        }
        //TODO Overwrite restaurant using gateway
    }
}
