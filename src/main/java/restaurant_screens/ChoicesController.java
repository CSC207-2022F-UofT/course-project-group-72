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

    ChoicesResponseModel create(ArrayList<Restaurant> sortedList) {

        //TODO: Update ChoicesRequestModel Class with necessary getters and setters needed.

        ChoicesRequestModel requestModel = new ChoicesRequestModel();

        return userInput.create(requestModel);
    }
}
