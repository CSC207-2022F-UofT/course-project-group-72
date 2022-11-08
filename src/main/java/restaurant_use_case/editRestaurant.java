package restaurant_use_case;

import entities.Restaurant;
import entities.RestaurantFactory;
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
        if (!gateway.existsByLocation(requestModel.getName())) {
            return presenter.prepareFailView("RESTAURANT DOES NOT EXIST");
        }

        Restaurant oldRestaurant = gateway.retrieveRestaurant(requestModel.getLocation());

        //TODO Overwrite restaurant using gateway
        LocalDateTime now = LocalDateTime.now();
        RestaurantDSRequestModel saveData = new RestaurantDSRequestModel(
                requestModel.getOwner().getUsername(),
                requestModel.getName(),
                requestModel.getLocation(),
                requestModel.getCuisineType(),
                requestModel.getPriceBucket(),
                requestModel.getAvgStars(),
                requestModel.getReviews() //convert to string identifiers
        );
        gateway.save(saveData);

        RestaurantResponseModel successResponseModel =
                new RestaurantResponseModel(oldRestaurant,
                        now.toString(), "edited");
        return presenter.prepareSuccessView(successResponseModel);
    }
}
