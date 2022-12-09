package user_use_case.screens;

import entities.*;
import filtering_use_case.screens.HomeScreenView;
import global.*;
import review_use_case.gateways.ReviewGateway;
import restaurant_use_case.interactors.FileRestaurant;
import user_use_case.controllers.UpgradeUserController;
import user_use_case.gateways.UserGateway;
import user_use_case.interactors.UpgradeUserInputBoundary;
import user_use_case.interactors.UpgradeUserInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ProfileScreen extends IFrame {

    User profile;

    String profile_name;

    User user;

    JPanel profile_screen_window;

    IFrame previousFrame;

    ArrayList<Review> reviews;
    ArrayList<Restaurant> restaurants;

    FileRestaurant restaurant_gateway;

    ReviewGateway reviewGateway;

    UpgradeUserController upgradeUserController;

    public ProfileScreen(IFrame previousFrame, User user, String profile_name) {
//      Make gatewaus and handle input variables
        UserGateway userGateway = new UserGateway();

        this.profile_name = profile_name;
        this.previousFrame = previousFrame;
        UpgradeUserInputBoundary inputBoundary = new UpgradeUserInteractor(new OwnerFactory(), userGateway);
        this.upgradeUserController = new UpgradeUserController(inputBoundary);

        this.profile = userGateway.getUser(profile_name);
        this.user = user;
        try {
            this.restaurant_gateway = new FileRestaurant("./main/Java/Databases/RestaurantDatabase.csv");
        } catch (IOException exception) {

        }

        this.reviewGateway = new ReviewGateway();
        try {
            this.reviews = this.reviewGateway.getReviews(this.profile.getPast_reviews());
        } catch (FileNotFoundException e) {
            this.reviews = new ArrayList<>();
        }

//        Make panel to attach everything to, set layout
        profile_screen_window = new JPanel();
        profile_screen_window.setLayout(new BoxLayout(profile_screen_window, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(this.profile.getUsername());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//        Initialize star counting for average
        int totalStars = 0;

        JPanel reviewsPanel = new JPanel();

//        State variables describing who is logged in.
        boolean isOwner = this.user instanceof OwnerUser;
        boolean isLoggedIn = this.user.getUsername().equals(profile_name);

//        Get restaurants and loop through to display them.
        this.restaurants = new ArrayList<Restaurant>();
        if (isOwner) {
            OwnerFactory factory = new OwnerFactory();
            OwnerUser temporaryUser = factory.reintialize(this.user.getUsername(), this.user.getPassword(), this.user.getPast_reviews(), this.user.getLikedReviews(), this.user.getReceived_reports(), this.user.isBanned(), true, ((OwnerUser) this.user).getOwnedRestaurants());
//            if (temporaryUser.getOwnedRestaurants().size() > 1) {
//                for (String restaurant : temporaryUser.getOwnedRestaurants()) {
//                    this.restaurants.add(restaurant_gateway.retrieveRestaurant(restaurant));
//                }
//            }
        }

//        Upgrade user section with button and actionlistener.

        if (!isOwner && isLoggedIn) {
            JButton upgradeButton = new JButton("Upgrade profile to Owner");
            upgradeButton.setOpaque(true);

            UpgradeUserActionListener upgradeUserActionListener = new UpgradeUserActionListener(this.user, this.upgradeUserController);
            upgradeButton.addActionListener(upgradeUserActionListener);
            profile_screen_window.add(upgradeButton);
        }


//        Reviews section showing past reviews of this user.

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


//        Creating necessary elements for displaying restaurants
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

//        Create restaurant button if the logged in user viewing the page is an owner.
        if (isLoggedIn && isOwner) {
            JButton createRestaurantButton = new JButton("Create Restaurant");
            CreateRestaurantActionListener createRestaurantActionListener = new CreateRestaurantActionListener(this, userGateway, this.restaurant_gateway, (OwnerUser) this.user);
            createRestaurantButton.addActionListener(createRestaurantActionListener);
            profile_screen_window.add(createRestaurantButton);
        }

        int averageStars = 0;
        if (this.reviews.size() > 0 && totalStars > 0) {
            averageStars = (int) totalStars / this.reviews.size();
        }

        JLabel averageStarLabel = new JLabel(Integer.toString(averageStars) + " average stars");

        JLabel reviewsLabel = new JLabel("Reviews");

//        Set alignment of all components
        reviewsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        averageStarLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        reviewsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        restaurantsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        restaurantsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

//        Add all components
        profile_screen_window.add(reviewsLabel);
        profile_screen_window.add(averageStarLabel);
        profile_screen_window.add(reviewsPanel);
        profile_screen_window.add(restaurantsLabel);
        profile_screen_window.add(restaurantsPanel);

        JLabel test = new JLabel("HELLOOO");
//        test.setVisible(true);
//        this.add(test);


        profile_screen_window.setVisible(true);

//        this.add(profile_screen_window);
        this.setContentPane(profile_screen_window);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void refresh() {
        new ProfileScreen(this.previousFrame, this.user, this.profile_name);
        this.dispose();
    }

    @Override
    public void back() {
        this.previousFrame.setVisible(true);
        this.dispose();
    }

    @Override
    public void home(User user) {
        try {
            new HomeScreenView(user);
            this.dispose();
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.");
        }
    }
}
