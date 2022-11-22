package filtering_use_case;

import filtering_use_case.ChoicesInputBoundary;
import filtering_use_case.ChoicesRequestModel;
import filtering_use_case.ChoicesResponseModel;

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
                                       String inputSort,
                                       String inputDirection) {

        ChoicesRequestModel requestModel = new ChoicesRequestModel(inputSearch,
                inputLocation,
                inputCuisineType,
                inputPriceBucket,
                inputAvgStars,
                inputSort,
                inputDirection);

        return userInput.select(requestModel);}
}
