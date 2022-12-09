package filtering_use_case.interactors;
/**
 * The request model used in the selection process, this contains all user input from HomeScreenView
 */
public class ChoicesRequestModel {

    private final String inputSearch;
    private final String inputLocation;
    private final String inputCuisineType;
    private final int inputPriceBucket;
    private final double inputAvgStars;
    private final String inputDirection;



    public ChoicesRequestModel(String inputSearch, String inputLocation, String inputCuisineType, int inputPriceBucket,
                               double inputAvgStars, String inputDirection){

        this.inputSearch = inputSearch;
        this.inputLocation = inputLocation;
        this.inputCuisineType = inputCuisineType;
        this.inputPriceBucket = inputPriceBucket;
        this.inputAvgStars = inputAvgStars;
        this.inputDirection = inputDirection;

    }

    String getInputSearch() {return this.inputSearch;}
    String getInputLocation() {return this.inputLocation;}
    String getInputCuisineType() {return this.inputCuisineType;}
    int getInputPriceBucket() {return this.inputPriceBucket;}
    double getInputAvgStars() {return this.inputAvgStars;}
    String getInputDirection() {return this.inputDirection;}

}