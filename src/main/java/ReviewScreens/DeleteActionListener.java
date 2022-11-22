//Class responsible for holding a reference to a review, its restaurant and its user, as well as initiating the delete
//use case for those objects

package ReviewScreens;

import ReviewControllers.DeleteReviewController;
import ReviewInterfaces.ReviewGatewayInterface;
import user_use_cases.UserGatewayInterface;
import restaurant_use_case.RestaurantDSGateway;
import entities.Restaurant;
import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteActionListener implements ActionListener {

    //Attributes we need to pass to DeleteReviewScreen
    private final JFrame owner;
    private final DeleteReviewController deleteReviewController;
    private final ReviewGatewayInterface reviewGateway;
    private final UserGatewayInterface userGateway;
    private final RestaurantDSGateway restaurantGateway;
    private final Review review;
    private final User user;
    private final Restaurant restaurant;

    /*
    Constructor
     */
    public DeleteActionListener(JFrame owner, DeleteReviewController deleteReviewController,
                                ReviewGatewayInterface reviewGateway, UserGatewayInterface userGateway,
                                RestaurantDSGateway restaurantGateway, Review review, User user, Restaurant restaurant){
        this.owner = owner;
        this.deleteReviewController = deleteReviewController;
        this.reviewGateway = reviewGateway;
        this.userGateway = userGateway;
        this.restaurantGateway = restaurantGateway;
        this.review = review;
        this.user = user;
        this.restaurant = restaurant;
    }

    /*
    Create a new DeleteReviewScreen for the given review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        DeleteReviewScreen deleteScreen = new DeleteReviewScreen(this.owner, this.deleteReviewController,
                this.reviewGateway, this.userGateway, this.restaurantGateway, this.review, this.user, this.restaurant);
    }
}
