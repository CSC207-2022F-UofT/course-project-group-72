//Class responsible for bundling input data for delete use case

package ReviewInteractors;

import ReviewInterfaces.ReviewGatewayInterface;
import user_use_cases.UserGatewayInterface;
import restaurant_use_case.RestaurantDSGateway;
import entities.Review;
import entities.User;
import entities.Restaurant;

public class DeleteReviewRequestModel {
    private final ReviewGatewayInterface reviewGateway;
    private final UserGatewayInterface userGateway;
    private final RestaurantDSGateway restaurantGateway;
    private final Review review;
    private final User user;
    private final Restaurant restaurant;

    /*
    Constructor
     */
    public DeleteReviewRequestModel(ReviewGatewayInterface reviewGateway, UserGatewayInterface userGateway,
                                    RestaurantDSGateway restaurantGateway, Review review,
                                    User user, Restaurant restaurant){
        this.reviewGateway = reviewGateway;
        this.userGateway = userGateway;
        this.restaurantGateway = restaurantGateway;
        this.review = review;
        this.user = user;
        this.restaurant = restaurant;
    }

    //Methods to get attributes
    public ReviewGatewayInterface getReviewGateway() {return reviewGateway;}

    public UserGatewayInterface getUserGateway() {return userGateway;}

    public RestaurantDSGateway getRestaurantGateway() {return restaurantGateway;}

    public Review getReview() {return review;}

    public User getUser() {return user;}

    public Restaurant getRestaurant() {return restaurant;}
}
