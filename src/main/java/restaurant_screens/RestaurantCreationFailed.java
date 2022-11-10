package restaurant_screens;

public class RestaurantCreationFailed extends RuntimeException {
    public RestaurantCreationFailed(String error) {
        super(error);
    }
}
