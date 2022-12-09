package restaurant_feature.screens;

/**
 * The screen data that is required to update the screen
 */
public class RestaurantResponseModel {

    String restaurant;
    String creationTime;
    String operation;

    public RestaurantResponseModel(String restaurant, String creationTime, String operation) {
        this.restaurant = restaurant;
        this.creationTime = creationTime;
        this.operation = operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
