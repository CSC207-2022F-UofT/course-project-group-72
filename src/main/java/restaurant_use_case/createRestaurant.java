package restaurant_use_case;

import entities.Restaurant;
import entities.RestaurantFactory;

public class createRestaurant implements RestaurantInputBoundary{
    private final RestaurantFactory factory = new RestaurantFactory();

    @Override
    public RestaurantResponseModel create(RestaurantRequestModel requestModel) {

        Restaurant newRestaurant = factory.create(
                requestModel.getOwner(),
                requestModel.getName(),
                requestModel.getLocation(),
                requestModel.getCuisineType(),
                requestModel.getPriceBucket()
        );

        //TODO Gateway.save()

        //TODO return RequestModel: Decide on what the screen will show
        //TODO most likely a success or fail view, then update the page with the new restaurant

    }
}
