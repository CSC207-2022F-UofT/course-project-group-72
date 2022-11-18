package restaurant_use_case;

import restaurant_screens.RestaurantDeletePresenter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Delete Restaurant use case interactor, removes the Restaurant from the database
 */
public class deleteRestaurant implements RestaurantDeleteInputBoundary{
    /**
     * The Restaurant Gateway that manages the Restaurant database
     */
    private final RestaurantDSGateway gateway;
    /**
     * The Restaurant Presenter which updates the display to show that the
     * current Restaurant was deleted
     */
    private final RestaurantDeletePresenter presenter;

    /**
     *
     * @param dataGateway the Restaurant Gateway
     * @param presenter the Restaurant Presenter
     */
    public deleteRestaurant(RestaurantDSGateway dataGateway, RestaurantDeletePresenter presenter) {
        gateway = dataGateway;
        this.presenter = presenter;
    }

    /**
     *
     * @param requestModel the information on which Restaurant to delete
     * @return the new information to display in the form of a Response Model
     */
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
