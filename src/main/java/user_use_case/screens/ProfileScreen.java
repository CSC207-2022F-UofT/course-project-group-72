package user_use_case.screens;

import global.*;
import review_use_case.gateways.ReviewGateway;
import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.interactors.FileRestaurant;
import user_use_case.controllers.UpgradeUserController;
import user_use_case.gateways.UserGateway;
import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ProfileScreen extends JFrame implements IFrame {
    User profile;

    String profile_name;

    User user;

    JFrame profile_screen_window;

    IFrame previousFrame;

    ArrayList<Review> reviews;
    ArrayList<Restaurant> restaurants;

    FileRestaurant restaurant_gateway;

    ReviewGateway reviewGateway;

    UpgradeUserController upgradeUserController;

    public ProfileScreen(IFrame previousFrame, User user, String profile_name) {
        UserGateway userGateway = new UserGateway();

        this.profile_name = profile_name;
        this.previousFrame = previousFrame;
        this.upgradeUserController = new UpgradeUserController();

        this.profile = userGateway.getUser(profile_name);
        this.user = user;
        try {
            this.restaurant_gateway = new FileRestaurant("./main/Java/Databases/restaurants.csv");
        } catch (IOException exception) {

        }

        this.reviewGateway = new ReviewGateway();
        try {
            this.reviews = this.reviewGateway.getReviews(this.profile.getPast_reviews());
        } catch (FileNotFoundException e) {
            this.reviews = new ArrayList<>();
        }

        profile_screen_window = new JFrame();

        JLabel title = new JLabel(this.profile.getUsername());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        int totalStars = 0;

        JPanel reviewsPanel = new JPanel();

        boolean isOwner = this.user instanceof OwnerUser;
        boolean isLoggedIn = this.user.getUsername().equals(profile_name);

        this.restaurants = new ArrayList<>();
        if (isOwner) {
            OwnerUser temporaryUser = (OwnerUser) this.user;
            for (String restaurant : temporaryUser.getOwnedRestaurants()) {
                this.restaurants.add(restaurant_gateway.retrieveRestaurant(restaurant));
            }
        }

//        Upgrade user section

        if (!isOwner && isLoggedIn) {
            JButton upgradeButton = new JButton("Upgrade profile to Owner");

            UpgradeUserActionListener upgradeUserActionListener = new UpgradeUserActionListener(this.user);
            upgradeButton.addActionListener(upgradeUserActionListener);
            this.profile_screen_window.add(upgradeButton);
        }


//        Reviews section

        for (Review review : this.reviews) {
            JLabel restarauntName = new JLabel(review.getRestaurant());
            JLabel reviewText = new JLabel(review.getText());
            int starCount = review.getStars();
            JLabel stars = new JLabel(Integer.toString(starCount) + "/5 stars");



                JPanel panel = new JPanel();
                panel.add(restarauntName);
                panel.add(stars);
                panel.add(reviewText);

            reviewsPanel.add(panel);

            totalStars = totalStars + starCount;
        }


        JLabel restaurantsLabel = new JLabel("Restaurants");
        JPanel restaurantsPanel = new JPanel();


        for (Restaurant restaurant : this.restaurants) {
            JPanel tempPanel = new JPanel();
            JLabel restaurantName = new JLabel(restaurant.getName());
            JLabel restaurantLocation = new JLabel(restaurant.getLocation());
            JButton viewButton = new JButton("View");
            ViewRestaurantActionListener viewRestaurantActionListener = new ViewRestaurantActionListener(this.previousFrame, this.user, restaurant);
            viewButton.addActionListener(viewRestaurantActionListener);
            if (isLoggedIn) {
                JButton editButton = new JButton("Edit");
                EditRestaurantActionListener editRestaurantActionListener = new EditRestaurantActionListener(this, (OwnerUser) this.user, restaurant, restaurant_gateway);
                editButton.addActionListener(editRestaurantActionListener);
            }

            tempPanel.add(restaurantName);
            tempPanel.add(restaurantLocation);
            tempPanel.add(viewButton);
            restaurantsPanel.add(tempPanel);
        }

        if (isLoggedIn && isOwner) {
            JButton createRestaurantButton = new JButton("Create Restaurant");
            CreateRestaurantActionListener createRestaurantActionListener = new CreateRestaurantActionListener(this, userGateway, this.restaurant_gateway, (OwnerUser) this.user);
            createRestaurantButton.addActionListener(createRestaurantActionListener);
        }

        int averageStars = totalStars / this.reviews.size();

        JLabel averageStarLabel = new JLabel(Integer.toString(averageStars));

        JLabel reviewsLabel = new JLabel("Reviews");
        profile_screen_window.add(averageStarLabel);
        profile_screen_window.add(reviewsLabel);
        profile_screen_window.add(reviewsPanel);
        profile_screen_window.add(restaurantsLabel);
        profile_screen_window.add(restaurantsPanel);


        profile_screen_window.setVisible(true);
    }

    @Override
    public void refresh() {
        this.dispose();
        new ProfileScreen(this.previousFrame, this.user, this.profile_name);
    }

    @Override
    public void back() {
        JFrame frame = this.previousFrame;
        frame.setVisible(true);
        this.dispose();
    }
}

// TODO: refresh method grab everything and see if anything has changed
