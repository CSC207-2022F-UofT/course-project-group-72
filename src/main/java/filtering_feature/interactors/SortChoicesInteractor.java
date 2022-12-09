package filtering_feature.interactors;

import entities.Restaurant;
import filtering_feature.interfaces.ChoicesInputBoundary;
import filtering_feature.screens.ChoicesPresenter;
import filtering_feature.screens.ChoicesResponseModel;
import restaurant_feature.interfaces.RestaurantDSGateway;

import java.util.ArrayList;
import java.util.Objects;

import static filtering_feature.screens.HomeScreenView.*;

/**
 * Use Case Interactor for SortChoices
 */

public class SortChoicesInteractor implements ChoicesInputBoundary {

    /**
     * The Restaurant Gateway that is used to access and filter through the Restaurant Database
     */
    private final RestaurantDSGateway gateway;
    /**
     * The Choices Presenter that is used to update the view
     */
    private final ChoicesPresenter presenter;
    /**
     * The Sorting use case class that manages the sorting delegation from user selection
     */
    private final Sorting sortMethod;

    public SortChoicesInteractor(RestaurantDSGateway choicesGateway,
                                 ChoicesPresenter choicesPresenter,
                                 Sorting sorting){

        this.gateway = choicesGateway;
        this.presenter = choicesPresenter;
        this.sortMethod = sorting;
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
        sortMethod.sortList(sortedRestaurants, requestModel.getInputDirection());

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