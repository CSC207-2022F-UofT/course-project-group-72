package restaurant_use_case;

import entities.Restaurant;

public class RestaurantResponseModel {

    Restaurant restaurant;
    String creationTime;
    String operation;

    public RestaurantResponseModel(Restaurant restaurant, String creationTime, String operation) {
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
