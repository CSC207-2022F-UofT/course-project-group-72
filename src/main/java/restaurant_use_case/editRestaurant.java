package restaurant_use_case;

import entities.Restaurant;
import restaurant_screens.RestaurantPresenter;

import java.time.LocalDateTime;

public class editRestaurant implements RestaurantInputBoundary{
    private final RestaurantDSGateway gateway;
    private final RestaurantPresenter presenter;

    public editRestaurant(RestaurantDSGateway dataGateway,
                          RestaurantPresenter presenter) {
        gateway = dataGateway;
        this.presenter = presenter;
    }

    @Override
    public RestaurantResponseModel create(RestaurantRequestModel requestModel) {
        if (!gateway.existsByLocation(requestModel.getLocation())) {
            return presenter.prepareFailView("RESTAURANT DOES NOT EXIST");
        }

        Restaurant oldRestaurant = gateway.retrieveRestaurant(requestModel.getLocation());

        // Change the restaurant to have the updated values
        oldRestaurant.setName(requestModel.getName());
        oldRestaurant.setCuisineType(requestModel.getCuisineType());
        oldRestaurant.setPriceBucket(requestModel.getPriceBucket());


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
