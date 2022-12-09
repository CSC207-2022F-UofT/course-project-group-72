package filtering_use_case.interactors;

/**
 * Fail Screen RunTimeException (Choices Exception)
 */
public class RestaurantFilterFailed extends RuntimeException{

    public RestaurantFilterFailed(String error) {
        super(error);
    }
}