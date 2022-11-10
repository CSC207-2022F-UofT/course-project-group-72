package Models;

import Interfaces.ReviewGatewayInterface;
import Interfaces.UserGatewayInterface;
import Interfaces.RestaurantGatewayInterface;
import entities.Restaurant;
import entities.User;

public class WriteReviewRequestModel {
    private final ReviewGatewayInterface reviewGateway;
    private final UserGatewayInterface userGateway;
    private final RestaurantGatewayInterface restaurantGateway;
    private final User user;
    private final Restaurant restaurant;
    private final int stars;
    private final String text;

    public WriteReviewRequestModel(ReviewGatewayInterface reviewGateway, UserGatewayInterface userGateway,
                                   RestaurantGatewayInterface restaurantGateway, User user, Restaurant restaurant,
                                   int stars, String text){
        this.reviewGateway = reviewGateway;
        this.userGateway = userGateway;
        this.restaurantGateway = restaurantGateway;
        this.user = user;
        this.restaurant = restaurant;
        this.stars = stars;
        this.text = text;
    }

    public ReviewGatewayInterface getReviewGateway() {
        return reviewGateway;
    }

    public UserGatewayInterface getUserGateway() {
        return userGateway;
    }

    public RestaurantGatewayInterface getRestaurantGateway() {
        return restaurantGateway;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getStars() {
        return stars;
    }

    public String getText() {
        return text;
    }
}
