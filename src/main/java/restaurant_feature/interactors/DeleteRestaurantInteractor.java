package restaurant_feature.interactors;

import entities.OwnerUser;
import restaurant_feature.interfaces.RestaurantDeleteInputBoundary;
import restaurant_feature.screens.RestaurantResponseModel;
import restaurant_feature.interfaces.RestaurantDSGateway;
import restaurant_feature.screens.RestaurantDeletePresenter;
import user_feature.interfaces.UserGatewayInterface;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Delete Restaurant use case interactor, removes the Restaurant from the database
 */
public class DeleteRestaurantInteractor implements RestaurantDeleteInputBoundary {
    /**
     * The Restaurant Gateway that manages the Restaurant database
     */
    private final RestaurantDSGateway restaurantGateway;
    /**
     * The User Gateway that manages the User database
     */
    private final UserGatewayInterface userGateway;
    /**
     * The Restaurant Presenter which updates the display to show that the
     * current Restaurant was deleted
     */
    private final RestaurantDeletePresenter presenter;

    /**
     *
     * @param restaurantGateway the Restaurant Gateway responsible for managing the database
     * @param userGateway the User gateway responsible for managing the database
     * @param presenter the Restaurant Presenter
     */
    public DeleteRestaurantInteractor(RestaurantDSGateway restaurantGateway, UserGatewayInterface userGateway,
                                      RestaurantDeletePresenter presenter) {
        this.restaurantGateway = restaurantGateway;
        this.userGateway = userGateway;
        this.presenter = presenter;
    }

    /**
     *
     * @param requestModel the information on which Restaurant to delete
     * @return the new information to display in the form of a Response Model
     */
    @Override
    public RestaurantResponseModel delete(RestaurantDeleteRequestModel requestModel) {
        if (!restaurantGateway.existsByLocation(requestModel.getRestaurant().getLocation())) {
            // Checks if the restaurant is already in the system and can be edited
            return presenter.prepareFailView("RESTAURANT DOES NOT EXIST");
        } else if (!Objects.equals(requestModel.getRestaurant().getOwnerID(), requestModel.getOwner().getUsername())){
            // Checks if the restaurant is owned by the current user
            return presenter.prepareFailView("You do not own this restaurant");
        }

        // Retrieve and update the owner
        OwnerUser owner = requestModel.getOwner();
        owner.removeRestaurant(requestModel.getRestaurant());
        LocalDateTime now = LocalDateTime.now();
        try {
            userGateway.updateUser(owner);
            // Use the gateway to remove the restaurant
            restaurantGateway.deleteRestaurant(requestModel.getRestaurant().getLocation());
        } catch (Exception error) {
            return presenter.prepareFailView("Database error occurred");
        }
        return presenter.prepareSuccessView("deleted", requestModel.getRestaurant().getName(), now.toString());

    }
}
