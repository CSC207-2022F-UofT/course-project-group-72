package restaurant_use_case;

import entities.Restaurant;
import restaurant_screens.ChoicesPresenter;

import java.util.ArrayList;
import java.util.Objects;

public class sortChoices implements ChoicesInputBoundary{

    private final RestaurantDSGateway gateway;
    private final ChoicesPresenter presenter;

    public sortChoices(RestaurantDSGateway choicesGateway, ChoicesPresenter choicesPresenter){

        this.gateway = choicesGateway;
        this.presenter = choicesPresenter;
    }

    @Override
    public ChoicesResponseModel create(ChoicesRequestModel requestModel){

        ArrayList<Restaurant> allRestaurants = gateway.retrieveAllRestaurants();
        ArrayList<Restaurant> sortedRestaurants = new ArrayList<>();


        // TODO: Implement search query algorithm, how many consecutive letters should match?

        for (Restaurant i : allRestaurants){
            if((i.getPriceBucket() == requestModel.getInputPriceBucket() ||
                    i.getPriceBucket() == 0)

                && (Objects.equals(i.getLocation(), requestModel.getInputLocation()) ||
                    i.getLocation() == null)

                && (Objects.equals(i.getCuisineType(), requestModel.getInputCuisineType()) ||
                    i.getCuisineType() == null)

                && (i.getAvgStars() == requestModel.getInputAvgStars() ||
                        i.getAvgStars() == 0))
            {
                sortedRestaurants.add(i);}
        }

        // TODO: Implement sorting by sortMethod
        String sortMethod = requestModel.getInputSort();

        ChoicesResponseModel responseModel = new ChoicesResponseModel(sortedRestaurants);

        return presenter.prepareSuccessView(responseModel);




    }

}
