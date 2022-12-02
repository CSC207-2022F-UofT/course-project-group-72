package filtering_use_case.interactors;

public class RestaurantFilterFail extends RuntimeException{

    public RestaurantFilterFail(String error) {
        super(error);
    }
}