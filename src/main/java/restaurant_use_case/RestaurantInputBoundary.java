package restaurant_use_case;

public interface RestaurantInputBoundary {
    RestaurantResponseModel create(RestaurantRequestModel requestModel);
}
