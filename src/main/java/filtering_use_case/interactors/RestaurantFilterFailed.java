package filtering_use_case.interactors;

public class RestaurantFilterFailed extends RuntimeException{

    public RestaurantFilterFailed(String error) {
        super(error);
    }
}