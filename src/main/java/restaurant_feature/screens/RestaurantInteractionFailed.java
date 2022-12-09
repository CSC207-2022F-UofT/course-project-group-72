package restaurant_feature.screens;

/**
 * The error thrown when the interaction was not successfully completed
 */
public class RestaurantInteractionFailed extends RuntimeException {
    public RestaurantInteractionFailed(String error) {
        super(error);
    }
}
