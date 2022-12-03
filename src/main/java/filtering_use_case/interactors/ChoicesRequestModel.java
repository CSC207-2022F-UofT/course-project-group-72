package filtering_use_case.interactors;

public class ChoicesRequestModel {

    private String inputSearch;
    private String inputLocation;
    private String inputCuisineType;
    private int inputPriceBucket;
    private double inputAvgStars;
    private String inputDirection;



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