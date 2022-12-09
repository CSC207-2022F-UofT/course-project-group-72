//Class responsible for bundling the data required for the edit use case

package review_feature.interactors;

import entities.Restaurant;
import restaurant_feature.interfaces.RestaurantDSGateway;
import review_feature.interfaces.ReviewGatewayInterface;
import entities.Review;

public class EditReviewRequestModel {
    private final ReviewGatewayInterface reviewGateway;
    private final RestaurantDSGateway restaurantGateway;
    private final Review review;
    private final Restaurant restaurant;
    private final int stars;
    private final String text;

    /*
    Constructor
     */
    public EditReviewRequestModel(ReviewGatewayInterface reviewGateway, RestaurantDSGateway restaurantGateway,
                                  Review review, Restaurant restaurant, int stars, String text){
        this.reviewGateway = reviewGateway;
        this.restaurantGateway = restaurantGateway;
        this.review = review;
        this.restaurant = restaurant;
        this.stars = stars;
        this.text = text;
    }

    //Methods to retrieve attributes
    public ReviewGatewayInterface getReviewGateway() {return reviewGateway;}

    public RestaurantDSGateway getRestaurantGateway() {return restaurantGateway;}

    public Review getReview() {return review;}

    public Restaurant getRestaurant() {return restaurant;}

    public int getStars() {return stars;}

    public String getText() {return text;}

}
