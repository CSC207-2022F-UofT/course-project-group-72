package restaurant_feature.interactors;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_feature.interfaces.RestaurantInputBoundary;
import restaurant_feature.screens.RestaurantResponseModel;
import restaurant_feature.interfaces.RestaurantDSGateway;
import restaurant_feature.screens.RestaurantPresenter;

import java.time.LocalDateTime;

/**
 * The edit Restaurant use case interactor which changes the data of the current restaurant and
 * reflects that update in the database
 */
public class EditRestaurantInteractor implements RestaurantInputBoundary {
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
    public EditRestaurantInteractor(RestaurantDSGateway dataGateway,
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
        // Down Cast to get the additional Restaurant attribute
        RestaurantEditRequestModel editRequestModel = (RestaurantEditRequestModel)requestModel;
        if (!gateway.existsByLocation(requestModel.getLocation())) {
            // Checks if the restaurant exists in the system and can be deleted
            return presenter.prepareFailView("RESTAURANT DOES NOT EXIST");
        } else if (!requestModel.getOwner().getUsername().equals(editRequestModel.getRestaurant().getOwnerID())) {
            // Checks if the restaurant is owned by the OwnerUser
            return presenter.prepareFailView("You do not own this Restaurant");
        } else if (requestModel.getPriceBucket() < 0 || requestModel.getPriceBucket() > 5) {
            // Check if a valid price bucket was inputted
            return presenter.prepareFailView("Price Bucket out of range 1-5");
        }
        // Down Cast to access get Restaurant method of EditRequestModel
        Restaurant oldRestaurant = editRequestModel.getRestaurant();

        // Remove the unedited restaurant from the owner's list
        OwnerUser owner = requestModel.getOwner();
        owner.removeRestaurant(oldRestaurant);

        // Change the restaurant to have the updated values
        oldRestaurant.setName(requestModel.getName());
        oldRestaurant.setCuisineType(requestModel.getCuisineType());
        oldRestaurant.setPriceBucket(requestModel.getPriceBucket());

        LocalDateTime now = LocalDateTime.now();

        try {
            // Add back the edited restaurant to the owner's list
            owner.addRestaurant(oldRestaurant);
            // Save to database
            gateway.deleteRestaurant(requestModel.getLocation());
            gateway.save(oldRestaurant);

        } catch (Exception error) {
            return presenter.prepareFailView("Database error occurred");
        }

        RestaurantResponseModel successResponseModel =
                new RestaurantResponseModel(oldRestaurant.getName(),
                        now.toString(), "edited");
        return presenter.prepareSuccessView(successResponseModel);
    }
}
