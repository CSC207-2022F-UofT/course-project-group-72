package restaurant_use_case;

import entities.Restaurant;
import restaurant_screens.ChoicesPresenter;

import java.util.ArrayList;
import java.util.Objects;

public class sortChoices extends Sorting implements ChoicesInputBoundary{

    private final RestaurantDSGateway gateway;
    private final ChoicesPresenter presenter;

    public sortChoices(RestaurantDSGateway choicesGateway, ChoicesPresenter choicesPresenter){

        this.gateway = choicesGateway;
        this.presenter = choicesPresenter;
    }

    @Override
    // TODO: move this to the FileRestaurant (filtering)
    // TODO: Determine Location (Postal Code or City) If Postal Code use first 3 letters for nearby
    // TODO: Pricing change to buckets, not exact input price bucket (rating /10 for expense)
    // TODO: Cuisine Type stays the same
    // TODO: Average Stars should filter decimals, like 3.99 is categorized into 3

    public ChoicesResponseModel create(ChoicesRequestModel requestModel){

        ArrayList<Restaurant> matchedRestaurants = gateway.searchMatch(requestModel.getInputSearch());

        ArrayList<Restaurant> sortedRestaurants = new ArrayList<>();
        for (Restaurant restaurant : matchedRestaurants){

            // TODO: possibly have multi drop-down select?
            if((restaurant.getPriceBucket() == requestModel.getInputPriceBucket() ||
                    restaurant.getPriceBucket() == 0)

                && (Objects.equals(restaurant.getLocation().substring(0, 4),
                    requestModel.getInputLocation().substring(0, 4)) || restaurant.getLocation() == null)

                    // TODO: can change to tags, would make cuisine type an input or keep the same but
                    // choices are limited
                && (Objects.equals(restaurant.getCuisineType(), requestModel.getInputCuisineType()) ||
                    restaurant.getCuisineType() == null)

                && (Math.floor(restaurant.getAvgStars()) == requestModel.getInputAvgStars() ||
                    restaurant.getAvgStars() == 0))
            {
                sortedRestaurants.add(restaurant);}
        }
        sortList(sortedRestaurants);

        ChoicesResponseModel responseModel = new ChoicesResponseModel(sortedRestaurants);
        return presenter.prepareSuccessView(responseModel);

    }

    @Override
    public void sortList(ArrayList<Restaurant> sortedRestaurants) {
    }
}
