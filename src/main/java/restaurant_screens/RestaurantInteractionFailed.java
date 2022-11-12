package restaurant_screens;

public class RestaurantInteractionFailed extends RuntimeException {
    public RestaurantInteractionFailed(String error) {
        super(error);
    }
}
