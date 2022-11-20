package restaurant_use_case;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_screens.RestaurantPresenter;

import java.time.LocalDateTime;

/**
 * The edit Restaurant use case interactor which changes the data of the current restaurant and
 * reflects that update in the database
 */
public class editRestaurant implements RestaurantInputBoundary{
    /**
     * The Restaurant Gateway which manages the Restaurant Database
     */
    private final RestaurantDSGateway gateway;
    /**
     * The Restaurant Presenter which updates the screen
     */
    private final RestaurantPresenter presenter;

    /**
     *
     * @param dataGateway the RestaurantDSGateway
     * @param presenter the Restaurant Presenter
     */
    public editRestaurant(RestaurantDSGateway dataGateway,
                          RestaurantPresenter presenter) {
        gateway = dataGateway;
        this.presenter = presenter;
    }

    /**
     *
     * @param requestModel the information on which Restaurant to edit and the new parameters
     *                     of the Restaurant
     * @return the ResponseModel which is used by the screen to show the updated Restaurant
     */
    @Override
    public RestaurantResponseModel create(RestaurantRequestModel requestModel) {
        if (!gateway.existsByLocation(requestModel.getLocation())) {
            // Checks if the restaurant exists in the system and can be deleted
            return presenter.prepareFailView("RESTAURANT DOES NOT EXIST");
        }
        // Down Cast to access get Restaurant method of EditRequestModel
        RestaurantEditRequestModel requestModel1 = (RestaurantEditRequestModel)requestModel;
        Restaurant oldRestaurant = requestModel1.getRestaurant();

        // Remove the unedited restaurant from the owner's list
        OwnerUser owner = requestModel.getOwner();
        owner.removeRestaurant(oldRestaurant);

        // Change the restaurant to have the updated values
        oldRestaurant.setName(requestModel.getName());
        oldRestaurant.setCuisineType(requestModel.getCuisineType());
        oldRestaurant.setPriceBucket(requestModel.getPriceBucket());

        // Add back the edited restaurant to the owner's list
        owner.addRestaurant(oldRestaurant);

        // Save to database
        gateway.deleteRestaurant(requestModel.getLocation());
        LocalDateTime now = LocalDateTime.now();
        gateway.save(oldRestaurant);

        RestaurantResponseModel successResponseModel =
                new RestaurantResponseModel(oldRestaurant,
                        now.toString(), "edited");
        return presenter.prepareSuccessView(successResponseModel);
    }
}
