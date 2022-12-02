package filtering_use_case;

import entities.Restaurant;
import restaurant_use_case.gateways.RestaurantDSGateway;

import java.util.ArrayList;
import java.util.Objects;

public class sortChoices extends Sorting implements ChoicesInputBoundary{

    private final RestaurantDSGateway gateway;
    private final ChoicesPresenter presenter;

    public sortChoices(RestaurantDSGateway choicesGateway, ChoicesPresenter choicesPresenter){

        this.gateway = choicesGateway;
        this.presenter = choicesPresenter;
    }


    // TODO: move this to the FileRestaurant (filtering) {probably won't do as TA said this is alright}

    // TODO: Pricing change to buckets, not exact input price bucket (rating /10 for expense)
    @Override
    public ChoicesResponseModel select(ChoicesRequestModel requestModel){

        ArrayList<Restaurant> matchedRestaurants =
                gateway.searchMatch(requestModel.getInputSearch(), requestModel.getInputLocation());

        ArrayList<Restaurant> sortedRestaurants = new ArrayList<>();

        for (Restaurant restaurant : matchedRestaurants){

            // Possibly have multi drop-down select?
            if((restaurant.getPriceBucket() == requestModel.getInputPriceBucket() ||
                    requestModel.getInputPriceBucket() == 0)

                    // Determines Location (Postal Code) and uses the first 3 letters for nearby rest
                    //&& (Objects.equals(restaurant.getLocation().substring(0, 3),
                    //   requestModel.getInputLocation().substring(0, 3)) ||
                    //   Objects.equals(restaurant.getLocation(), ""))

                    // Can change to tags, would make cuisine type an input or keep the same but
                    // choices are limited
                    && (Objects.equals(restaurant.getCuisineType(), requestModel.getInputCuisineType()) ||
                    Objects.equals(requestModel.getInputCuisineType(), "No Preference"))

                    // Average Stars should filter decimals, like 3.99 is categorized into 3
                    && (Math.floor(restaurant.getAvgStars()) == requestModel.getInputAvgStars() ||
                    requestModel.getInputAvgStars() == 0))

            {
                sortedRestaurants.add(restaurant);}
        }
        //sortList(sortedRestaurants, requestModel.getInputDirection());

        ChoicesResponseModel responseModel = new ChoicesResponseModel(sortedRestaurants);
        return presenter.prepareSuccessView(responseModel);

    }

    @Override
    public void sortList(ArrayList<Restaurant> sortedRestaurants, String sortDirection) {

    }
}
