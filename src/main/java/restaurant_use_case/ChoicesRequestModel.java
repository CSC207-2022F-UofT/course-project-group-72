package restaurant_use_case;

import entities.OwnerUser;

public class ChoicesRequestModel {

    private String inputSearch;
    private String inputLocation;
    private String inputCuisineType;
    private int inputPriceBucket;
    private double inputAvgStars;
    private String inputSort;



    public ChoicesRequestModel(String inputSearch, String inputLocation, String inputCuisineType, int inputPriceBucket,
                               double inputAvgStars, String inputSort){

        this.inputSearch = inputSearch;
        this.inputLocation = inputLocation;
        this.inputCuisineType = inputCuisineType;
        this.inputPriceBucket = inputPriceBucket;
        this.inputAvgStars = inputAvgStars;
        this.inputSort = inputSort;

    }

    String getInputSearch() {return this.inputSearch;}
    String getInputLocation() {return this.inputLocation;}
    String getInputCuisineType() {return this.inputCuisineType;}
    int getInputPriceBucket() {return this.inputPriceBucket;}
    double getInputAvgStars() {return this.inputAvgStars;}
    String getInputSort() {return this.inputSort;}

}
