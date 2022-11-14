package restaurant_use_case;

import entities.Restaurant;
import entities.RestaurantFactory;
import restaurant_screens.RestaurantPresenter;

import java.time.LocalDateTime;

public class createRestaurant implements RestaurantInputBoundary{
    private final RestaurantFactory factory;
    private final RestaurantDSGateway gateway;
    private final RestaurantPresenter presenter;

    public createRestaurant(RestaurantFactory factory, RestaurantDSGateway dataGateway,
                            RestaurantPresenter presenter) {
        this.factory = factory;
        //not sure what is going on here, this.gateway throws an error
        gateway = dataGateway;
        this.presenter = presenter;
    }

    @Override
    public RestaurantResponseModel create(RestaurantRequestModel requestModel) {

        if (gateway.existsByLocation(requestModel.getLocation())) {
            return presenter.prepareFailView("INVALID LOCATION");
        } else if (requestModel.getLocation().length() == 0 |
                    requestModel.getName().length() == 0 |
                    requestModel.getCuisineType().length() == 0){
            return presenter.prepareFailView("Please fill in all of the fields");
        }
        Restaurant newRestaurant = factory.create(
                requestModel.getOwnerID(),
                requestModel.getName(),
                requestModel.getLocation(),
                requestModel.getCuisineType(),
                requestModel.getPriceBucket()
        );

        LocalDateTime now = LocalDateTime.now();
        RestaurantDSRequestModel saveData = new RestaurantDSRequestModel(
                requestModel.getOwnerID(),
                requestModel.getName(),
                requestModel.getLocation(),
                requestModel.getCuisineType(),
                requestModel.getPriceBucket()
        );
        gateway.save(saveData);

        RestaurantResponseModel successResponseModel =
                new RestaurantResponseModel(newRestaurant, now.toString(), "created");
        return presenter.prepareSuccessView(successResponseModel);


    }
}
