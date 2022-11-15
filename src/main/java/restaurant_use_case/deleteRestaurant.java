package restaurant_use_case;

import restaurant_screens.RestaurantDeletePresenter;

import java.time.LocalDateTime;
import java.util.Objects;

public class deleteRestaurant implements RestaurantDeleteInputBoundary{
    private final RestaurantDSGateway gateway;
    private final RestaurantDeletePresenter presenter;

    public deleteRestaurant(RestaurantDSGateway dataGateway, RestaurantDeletePresenter presenter) {
        gateway = dataGateway;
        this.presenter = presenter;
    }

    @Override
    public RestaurantResponseModel delete(RestaurantDeleteRequestModel requestModel) {
        if (!gateway.existsByLocation(requestModel.getRestaurant().getLocation())) {
            // Checks if the restaurant is already in the system and can be edited
            return presenter.prepareFailView("RESTAURANT DOES NOT EXIST");
        } else if (!Objects.equals(requestModel.getRestaurant().getOwnerID(), requestModel.getOwner().getUsername())){
            // Checks if the restaurant is owned by the current user
            return presenter.prepareFailView("You do not own this restaurant");
        }

        LocalDateTime now = LocalDateTime.now();
        //reinitializing restaurant here is necessary may be changed later
        gateway.deleteRestaurant(requestModel.getRestaurant().getLocation());
        return presenter.prepareSuccessView("deleted", requestModel.getRestaurant().getName(), now.toString());

    }
}
