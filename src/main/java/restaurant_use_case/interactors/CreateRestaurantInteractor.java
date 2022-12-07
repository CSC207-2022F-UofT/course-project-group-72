package restaurant_use_case.interactors;

import entities.OwnerUser;
import entities.Restaurant;
import entities.RestaurantFactory;
import restaurant_use_case.interfaces.RestaurantInputBoundary;
import restaurant_use_case.screens.RestaurantResponseModel;
import restaurant_use_case.gateways.RestaurantDSGateway;
import restaurant_use_case.screens.RestaurantPresenter;
import user_use_case.interfaces.UserGatewayInterface;

import java.time.LocalDateTime;

/**
 * The create restaurant use case interactor
 */
public class CreateRestaurantInteractor implements RestaurantInputBoundary {
    /**
     * The Restaurant factory used to create a new Restaurant
     */
    private final RestaurantFactory factory;
    /**
     * The Restaurant Gateway that manages the Restaurant Database
     */
    private final RestaurantDSGateway restaurantGateway;
    /**
     * The Restaurant Presenter that sends information to the screen
     */
    private final RestaurantPresenter presenter;
    /**
     * The User Gateway that manages the User Database
     */
    private final UserGatewayInterface userGateway;

    /**
     *
     * @param factory the RestaurantFactory that creates
     * @param dataGateway the RestaurantGateway that manages the Restaurant database
     * @param userGateway the UserGateway that manages the User database
     * @param presenter the Presenter that updates the screen with the new Restaurant
     */
    public CreateRestaurantInteractor(RestaurantFactory factory, RestaurantDSGateway dataGateway,
                                      UserGatewayInterface userGateway, RestaurantPresenter presenter) {
        this.factory = factory;
        //not sure what is going on here, this.gateway throws an error
        restaurantGateway = dataGateway;
        this.userGateway = userGateway;
        this.presenter = presenter;
    }

    @Override
    public RestaurantResponseModel create(RestaurantRequestModel requestModel) {

        if (restaurantGateway.existsByLocation(requestModel.getLocation())) {
            // Checks if there is another restaurant at that same location, meaning they inputed the wrong location
            return presenter.prepareFailView("INVALID LOCATION");
        } else if (!requestModel.getLocation().matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$")){
            // Checks if the location string matches the postal code regex
            return presenter.prepareFailView
                    ("Please fill in your location as your postal code in the form: A1B 2C3");
        }else if (requestModel.getLocation().length() == 0 |
                    requestModel.getName().length() == 0 |
                    requestModel.getCuisineType().length() == 0){
            // Checks if the required fields were left empty
            //  - the price bucket is filled by default 0
            return presenter.prepareFailView("Please fill in all of the required fields");
        }
        Restaurant newRestaurant = factory.create(
                requestModel.getOwner().getUsername(),
                requestModel.getName(),
                requestModel.getLocation(),
                requestModel.getCuisineType(),
                requestModel.getPriceBucket()
        );

        // Adds the new Restaurant to the Owner
        OwnerUser ownerUser = requestModel.getOwner();
        ownerUser.addRestaurant(newRestaurant);
        // Updates the User in the database
        LocalDateTime now = LocalDateTime.now();
        try {
            restaurantGateway.save(newRestaurant);
            userGateway.updateUser(ownerUser);
        } catch (Exception error) {
            return presenter.prepareFailView("Database error occurred");
        }

        RestaurantResponseModel successResponseModel =
                new RestaurantResponseModel(newRestaurant.getName(), now.toString(), "created");
        return presenter.prepareSuccessView(successResponseModel);


    }
}
