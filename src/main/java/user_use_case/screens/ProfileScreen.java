package user_use_case.screens;

import global.CreateRestaurantActionListener;
import global.EditRestaurantActionListener;
import global.IFrame;
import global.ViewRestaurantActionListener;
import review_use_case.gateways.ReviewGateway;
import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.interactors.FileRestaurant;
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

public class ProfileScreen extends JFrame implements ActionListener, IFrame {
    User profile;

    User user;

    JFrame profile_screen_window;

    ArrayList<Review> reviews;
    ArrayList<Restaurant> restaurants;

    FileRestaurant restaurant_gateway;

    public ProfileScreen(User user, String profile_name) {
        UserGateway userGateway = new UserGateway();

        this.profile = userGateway.getUser(profile_name);
        this.user = user;
        try {
            this.restaurant_gateway = new FileRestaurant("./main/Java/Databases/restaurants.csv");
        } catch (IOException exception) {

        }

        ReviewGateway reviewGateway = new ReviewGateway();
        try {
            this.reviews = reviewGateway.getReviews(profile.getPast_reviews());
        } catch (FileNotFoundException e) {
            this.reviews = new ArrayList<>();
        }

        profile_screen_window = new JFrame();

        JLabel title = new JLabel(profile.getUsername());
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
            ViewRestaurantActionListener viewRestaurantActionListener = new ViewRestaurantActionListener(this.user, restaurant);
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
        profile_screen_window.add(reviewsLabel);
        profile_screen_window.add(reviewsPanel);
        profile_screen_window.add(restaurantsLabel);
        profile_screen_window.add(restaurantsPanel);


        profile_screen_window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void refresh(Restaurant restaurant) {

    }
}
