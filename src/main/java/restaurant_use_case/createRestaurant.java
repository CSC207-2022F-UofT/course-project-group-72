package restaurant_use_case;

import entities.Restaurant;
import entities.RestaurantFactory;
import restaurant_screens.RestaurantPresenter;
import user_use_cases.UserDatabaseGateway;

import java.time.LocalDateTime;

public class createRestaurant implements RestaurantInputBoundary{
    private final RestaurantFactory factory;
    private final RestaurantDSGateway restaurantGateway;
    private final RestaurantPresenter presenter;

    private final UserDatabaseGateway userGateway;

    public createRestaurant(RestaurantFactory factory, RestaurantDSGateway dataGateway,
                            UserDatabaseGateway userGateway, RestaurantPresenter presenter) {
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

        requestModel.getOwner().addRestaurant(newRestaurant);

        LocalDateTime now = LocalDateTime.now();
        restaurantGateway.save(newRestaurant);
        userGateway.update(OwnerUser);

        RestaurantResponseModel successResponseModel =
                new RestaurantResponseModel(newRestaurant, now.toString(), "created");
        return presenter.prepareSuccessView(successResponseModel);


    }
}
