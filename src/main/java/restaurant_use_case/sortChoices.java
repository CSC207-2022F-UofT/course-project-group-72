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
    public ChoicesResponseModel create(ChoicesRequestModel requestModel){

        ArrayList<Restaurant> matchedRestaurants = gateway.searchMatch(requestModel.getInputSearch());

        ArrayList<Restaurant> sortedRestaurants = new ArrayList<>();
        for (Restaurant restaurant : matchedRestaurants){
            if((restaurant.getPriceBucket() == requestModel.getInputPriceBucket() ||
                    restaurant.getPriceBucket() == 0)

                && (Objects.equals(restaurant.getLocation(), requestModel.getInputLocation()) ||
                    restaurant.getLocation() == null)

                && (Objects.equals(restaurant.getCuisineType(), requestModel.getInputCuisineType()) ||
                    restaurant.getCuisineType() == null)

                && (restaurant.getAvgStars() == requestModel.getInputAvgStars() ||
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
