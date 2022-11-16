package restaurant_screens;

import entities.Restaurant;
import restaurant_use_case.ChoicesInputBoundary;
import restaurant_use_case.ChoicesRequestModel;
import restaurant_use_case.ChoicesResponseModel;
import restaurant_use_case.RestaurantInputBoundary;

import java.util.ArrayList;

public class ChoicesController {

    final ChoicesInputBoundary userInput;

    public ChoicesController(ChoicesInputBoundary selections) {
        this.userInput = selections;
    }

    ChoicesResponseModel create(String inputSearch, int inputPriceBucket, String inputLocation,
                                String inputCuisineType, double inputAvgStars, String inputSort) {

        ChoicesRequestModel requestModel = new ChoicesRequestModel(inputSearch, inputPriceBucket, inputLocation,
                inputCuisineType, inputAvgStars, inputSort);

        return userInput.create(requestModel);}
}
