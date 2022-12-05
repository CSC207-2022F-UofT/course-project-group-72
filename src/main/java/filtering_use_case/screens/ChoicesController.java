package filtering_use_case.screens;

import filtering_use_case.interfaces.ChoicesInputBoundary;
import filtering_use_case.interactors.ChoicesRequestModel;

public class ChoicesController {

    final ChoicesInputBoundary userInput;

    public ChoicesController(ChoicesInputBoundary selections) {
        this.userInput = selections;
    }

    public ChoicesResponseModel select(String inputSearch,
                                       String inputLocation,
                                       String inputCuisineType,
                                       int inputPriceBucket,
                                       double inputAvgStars,
                                       String inputDirection) {

        ChoicesRequestModel requestModel = new ChoicesRequestModel(inputSearch,
                inputLocation,
                inputCuisineType,
                inputPriceBucket,
                inputAvgStars,
                inputDirection);

        return userInput.select(requestModel);}
}