package filtering_use_case.interactors;

import entities.Restaurant;
import filtering_use_case.interfaces.ChoicesInputBoundary;
import filtering_use_case.screens.ChoicesPresenter;
import filtering_use_case.screens.ChoicesResponseModel;
import restaurant_use_case.gateways.RestaurantDSGateway;

import java.util.ArrayList;
import java.util.Objects;

import static filtering_use_case.screens.HomeScreenView.*;

public class SortChoicesInteractor implements ChoicesInputBoundary {

    private final RestaurantDSGateway gateway;
    private final ChoicesPresenter presenter;

    public SortChoicesInteractor(RestaurantDSGateway choicesGateway, ChoicesPresenter choicesPresenter){

        this.gateway = choicesGateway;
        this.presenter = choicesPresenter;
    }

    @Override
    public ChoicesResponseModel select(ChoicesRequestModel requestModel){

        ArrayList<Restaurant> matchedRestaurants =
                gateway.searchMatch(requestModel.getInputSearch(), requestModel.getInputLocation());

        ArrayList<Restaurant> sortedRestaurants = new ArrayList<>();

        for (Restaurant restaurant : matchedRestaurants){

            // Filter Selections
            if(
                // Price Match or Filter Not Selected (Selected 0)
                    (restaurant.getPriceBucket() == requestModel.getInputPriceBucket() ||
                            requestModel.getInputPriceBucket() == 0)

                            // Cuisine Match or Filter Not Selected (Selected "No Preference")
                            && (Objects.equals(restaurant.getCuisineType(), requestModel.getInputCuisineType()) ||
                            Objects.equals(requestModel.getInputCuisineType(), NO_PREFERENCE))

                            // Average Stars should filter decimals, like 3.99 is categorized into 3
                            && (Math.floor(restaurant.getAvgStars()) == requestModel.getInputAvgStars() ||
                            requestModel.getInputAvgStars() == 0))

            // If Restaurant matches selections, add to sortedRestaurants ArrayList
            {
                sortedRestaurants.add(restaurant);}
        }

        // Sort Restaurants by RadioButtonPress
        if (requestModel.getInputSort() == PRICE) {
            SortPrice.sortList(sortedRestaurants, requestModel.getInputDirection());
        }

        else if (requestModel.getInputSort() == AVG_STARS) {
            SortAvgStars.sortList(sortedRestaurants, requestModel.getInputDirection());
        }

        else if (requestModel.getInputSort() == NAME) {
            SortName.sortList(sortedRestaurants, requestModel.getInputDirection());
        }

        // Create a Response Model
        ChoicesResponseModel responseModel = new ChoicesResponseModel(sortedRestaurants);

        // if Sorted Restaurants list is empty
        if (sortedRestaurants.size() == 0) {
            return presenter.prepareFailView("NO RESTAURANTS MATCH SELECTIONS. PLEASE TRY AGAIN");
        }
        else
            // Send to Presenter
            return presenter.prepareSuccessView(responseModel);

    }

}