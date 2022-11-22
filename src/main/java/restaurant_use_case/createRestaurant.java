package restaurant_use_case;

import entities.OwnerUser;
import entities.Restaurant;
import entities.RestaurantFactory;
import restaurant_screens.RestaurantPresenter;
import user_use_cases.UserGatewayInterface;

import java.time.LocalDateTime;

/**
 * The create restaurant use case interactor
 */
public class createRestaurant implements RestaurantInputBoundary{
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
    public createRestaurant(RestaurantFactory factory, RestaurantDSGateway dataGateway,
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
        } else if (requestModel.getLocation().length() == 0 |
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
        restaurantGateway.save(newRestaurant);
        userGateway.updateUser(ownerUser);

        RestaurantResponseModel successResponseModel =
                new RestaurantResponseModel(newRestaurant, now.toString(), "created");
        return presenter.prepareSuccessView(successResponseModel);


    }
}
