package restaurant_use_case;

import entities.OwnerUser;

public class ChoicesRequestModel {

    private String inputSearch;
    private int inputPriceBucket;
    private String inputLocation;
    private String inputCuisineType;
    private double inputAvgStars;
    private String inputSort;



    public ChoicesRequestModel(String inputSearch, int inputPriceBucket, String inputLocation,
                               String inputCuisineType, double inputAvgStars, String inputSort){

        this.inputSearch = inputSearch;
        this.inputPriceBucket = inputPriceBucket;
        this.inputLocation = inputLocation;
        this.inputCuisineType = inputCuisineType;
        this.inputAvgStars = inputAvgStars;
        this.inputSort = inputSort;

    }

    String getInputSearch() {return this.inputSearch;}
    int getInputPriceBucket() {return this.inputPriceBucket;}
    String getInputLocation() {return this.inputLocation;}
    String getInputCuisineType() {return this.inputCuisineType;}
    double getInputAvgStars() {return this.inputAvgStars;}
    String getInputSort() {return this.inputSort;}

}
