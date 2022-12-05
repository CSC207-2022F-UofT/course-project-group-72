package review_use_case.screens;

import entities.Restaurant;
import entities.User;
import restaurant_use_case.gateways.RestaurantDSGateway;
import review_use_case.controllers.WriteReviewController;
import review_use_case.interfaces.ReviewGatewayInterface;
import user_use_case.interfaces.UserGatewayInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteReviewActionListener implements ActionListener {

    private final RestaurantView restaurantView;
    private final WriteReviewController writeReviewController;
    private final ReviewGatewayInterface reviewGateway;
    private final UserGatewayInterface userGateway;
    private final RestaurantDSGateway restaurantGateway;
    private final User user;
    private final Restaurant restaurant;

    public WriteReviewActionListener(RestaurantView restaurantView, WriteReviewController writeReviewController,
                                     ReviewGatewayInterface reviewGateway, UserGatewayInterface userGateway,
                                     RestaurantDSGateway restaurantGateway, User user, Restaurant restaurant){
        this.restaurantView = restaurantView;
        this.writeReviewController = writeReviewController;
        this.reviewGateway = reviewGateway;
        this.userGateway = userGateway;
        this.restaurantGateway = restaurantGateway;
        this.user = user;
        this.restaurant = restaurant;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Check if they already reviewed this restaurant
        //NOTE: I would like for the user to have a reviewed restaurants attribute, change if that gets implemented
        boolean check = true;
        for (String reviewID : this.user.getPast_reviews()) {
            if (this.restaurant.getReviewIDs().contains(reviewID)) {
                check = false;
                break;
            }
        }
        //Launch the proper view
        if (check) {
            new WriteReviewScreen(this.restaurantView, this.writeReviewController, this.reviewGateway,
                    this.userGateway, this.restaurantGateway, this.user, this.restaurant);
        } else {
            JOptionPane.showMessageDialog(this.restaurantView, "You've already written a review for this " +
                    "restaurant. Please edit your previous review instead.");
        }
    }
}
