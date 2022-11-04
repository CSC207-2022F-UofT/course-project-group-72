package restaurant_use_case;

import entities.Restaurant;
import entities.RestaurantFactory;

public class createRestaurant implements RestaurantInputBoundary{
    private final RestaurantFactory factory;
    private final RestaurantDSGateway gateway;

    public createRestaurant(RestaurantFactory factory, RestaurantDSGateway dataGateway) {
        this.factory = factory;
        //not sure what is going on here, this.gateway throws an error
        gateway = dataGateway;
    }

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
        RestaurantDSRequestModel saveData = new RestaurantDSRequestModel();
        gateway.save(saveData);

        //TODO return RequestModel: Decide on what the screen will show
        //TODO most likely a success or fail view, then update the page with the new restaurant

    }
}
