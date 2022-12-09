package filtering_feature.screens;

import filtering_feature.interfaces.ChoicesInputBoundary;
import filtering_feature.interactors.ChoicesRequestModel;

/**
 * The Choices Controller for handling the user's filter and sort selections
 */
public class ChoicesController {
    /**
     * The modification interactor
     */
    final ChoicesInputBoundary userInput;

    /**
     *
     * @param selections the desired interactor to be used
     */
    public ChoicesController(ChoicesInputBoundary selections) {
        this.userInput = selections;
    }

    /**
     *  Used for filtering and sorting the restaurant database based on the user's selections
     *
     * @param inputSearch The text query that the user typed in to search for a specific restaurant's name
     * @param inputLocation The text query that the user typed in to limit restaurant listings to a specific location
     * @param inputCuisineType The drop-down menu selection that the user chose for their preferred food cuisine
     * @param inputPriceBucket The drop-down menu selection that the user chose for their preferred menu pricing
     * @param inputAvgStars The drop-down menu selection that the user chose for their preferred restaurant rating
     * @param inputDirection The direction in which the user would like to sort their search (Ascending or Descending)
     * @return the result of the interaction
     */
    public ChoicesResponseModel select(String inputSearch,
                                       String inputLocation,
                                       String inputCuisineType,
                                       int inputPriceBucket,
                                       double inputAvgStars,
                                       String inputDirection)
    {

        ChoicesRequestModel requestModel = new ChoicesRequestModel(inputSearch,
                inputLocation,
                inputCuisineType,
                inputPriceBucket,
                inputAvgStars,
                inputDirection);

        return userInput.select(requestModel);
    }
}