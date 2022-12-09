//Class responsible for bundling the input data required for the write review use case

package review_feature.interactors;

import review_feature.interfaces.ReviewGatewayInterface;
import user_feature.interfaces.UserGatewayInterface;
import restaurant_feature.interfaces.RestaurantDSGateway;
import entities.Restaurant;
import entities.User;

public class WriteReviewRequestModel {
    private final ReviewGatewayInterface reviewGateway;
    private final UserGatewayInterface userGateway;
    private final RestaurantDSGateway restaurantGateway;
    private final User user;
    private final Restaurant restaurant;
    private final int stars;
    private final String text;

    /*
    Constructor
     */
    public WriteReviewRequestModel(ReviewGatewayInterface reviewGateway, UserGatewayInterface userGateway,
                                   RestaurantDSGateway restaurantGateway, User user, Restaurant restaurant,
                                   int stars, String text){
        this.reviewGateway = reviewGateway;
        this.userGateway = userGateway;
        this.restaurantGateway = restaurantGateway;
        this.user = user;
        this.restaurant = restaurant;
        this.stars = stars;
        this.text = text;
    }

    //Methods to retrieve attributes
    public ReviewGatewayInterface getReviewGateway() {return reviewGateway;}

    public UserGatewayInterface getUserGateway() {return userGateway;}

    public RestaurantDSGateway getRestaurantGateway() {return restaurantGateway;}

    public User getUser() {return user;}

    public Restaurant getRestaurant() {return restaurant;}

    public int getStars() {return stars;}

    public String getText() {return text;}
}
