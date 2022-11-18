package restaurant_use_case;

public class ChoicesDSRequestModel {

    private final String inputSearch;
    private final int inputPriceBucket;
    private final String inputLocation;
    private final String inputCuisineType;
    private final double inputAvgStars;


    public ChoicesDSRequestModel(String inputSearch, int inputPriceBucket, String inputLocation,
                                 String inputCuisineType, double inputAvgStars) {

        this.inputSearch = inputSearch;
        this.inputPriceBucket = inputPriceBucket;
        this.inputLocation = inputLocation;
        this.inputCuisineType = inputCuisineType;
        this.inputAvgStars = inputAvgStars;
    }

    String getInputSearch() {
        return this.inputSearch;
    }

    int getInputPriceBucket() {
        return this.inputPriceBucket;
    }

    String getInputLocation() {
        return this.inputLocation;
    }

    String getInputCuisineType() {
        return this.inputCuisineType;
    }

    double getInputAvgStars() {
        return this.inputAvgStars;
    }


}
