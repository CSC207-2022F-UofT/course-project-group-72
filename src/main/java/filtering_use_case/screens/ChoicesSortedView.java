package filtering_use_case.screens;
import entities.Restaurant;
import entities.User;
import global.IFrame;
import global.ViewRestaurantActionListener;
import restaurant_use_case.gateways.RestaurantDSGateway;
import restaurant_use_case.interactors.FileRestaurant;
import user_use_case.gateways.UserGateway;
import user_use_case.interfaces.UserGatewayInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ChoicesSortedView extends IFrame implements ActionListener{
    //TODO: java documentation
    JLabel restaurantName;
    JLabel restaurantPrice;
    JLabel restaurantLocation;
    JLabel restaurantCuisineType;
    JLabel restaurantAvgStars;
    JButton viewRestaurantButton;
    JFrame previousFrame;
    ArrayList<Restaurant> sortedList;
    User user;

    public ChoicesSortedView(JFrame previousFrame, ArrayList<Restaurant> sortedList, User user) {

        // Save objects for us in IFrame methods
        this.previousFrame = previousFrame;
        this.sortedList = sortedList;
        this.user = user;

        //TODO: organize/format components

        // Create the back and home buttons (Uniform Implementation)
        JButton backButton = new JButton("Back");
        backButton.setFont(backButton.getFont().deriveFont(12F));
        backButton.setOpaque(true);
        backButton.addActionListener(this);

        JButton homeButton = new JButton("Home");
        homeButton.setFont(homeButton.getFont().deriveFont(12F));
        homeButton.setOpaque(true);
        homeButton.addActionListener(this);

        // Create a panel to hold the back and home buttons, then add those buttons (Uniform Implementation)
        JPanel IPanel = new JPanel();
        IPanel.setLayout(new BoxLayout(IPanel, BoxLayout.X_AXIS));
        IPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        IPanel.setVisible(true);
        IPanel.add(backButton);
        IPanel.add(homeButton);

        this.add(IPanel);

        // Setup Layout for Restaurant List
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Loop through sorted restaurants list
        for (Restaurant restaurant : sortedList) {
            // Text Components (Restaurant Information)
            restaurantName = new JLabel("Name: " + restaurant.getName());
            restaurantPrice = new JLabel("Price Rating($): " + restaurant.getPriceBucket());
            restaurantLocation = new JLabel("Location: " + restaurant.getLocation());
            restaurantCuisineType = new JLabel("Cuisine: " + restaurant.getCuisineType());
            restaurantAvgStars = new JLabel("Star Rating(/5): " + restaurant.getAvgStars());

            // View Restaurant Button
            viewRestaurantButton = new JButton("View Restaurant");

            // Add all elements to a single JPanel
            JPanel restaurantPanel = new JPanel();
            restaurantPanel.add(restaurantName);
            restaurantPanel.add(restaurantPrice);
            restaurantPanel.add(restaurantLocation);
            restaurantPanel.add(restaurantCuisineType);
            restaurantPanel.add(viewRestaurantButton);

            // Add ViewRestaurantActionListener to viewRestaurantButton
            viewRestaurantButton.addActionListener(new ViewRestaurantActionListener(this, user, restaurant));

            this.add(restaurantPanel);
        }

        // Window options
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setVisible(true);
        this.repaint();

    }

    /**
     * Helper method to update the current User
     *
     * @return the updated User from database
     */
    private User updateUser() {
        UserGatewayInterface userGateway = new UserGateway();
        return userGateway.getUser(user.getUsername());
    }

    @Override
    public void refresh() {
        try {
            // Try to construct the Restaurant Gateway
            RestaurantDSGateway restaurantGateway
                    = new FileRestaurant("src/main/java/Databases/RestaurantDatabase.csv");

            // Retrieve the new values of any changed Restaurants
            User updatedUser = updateUser();
            ArrayList<Restaurant> updatedSortedList = new ArrayList<>();
            for (Restaurant restaurant : sortedList) {
                String location = restaurant.getLocation();
                // If the Restaurant exists and therefore was not deleted get the new information
                if (restaurantGateway.existsByLocation(location)) {
                    updatedSortedList.add(restaurantGateway.retrieveRestaurant(location));
                }
            }
            // Reload the screen
            new ChoicesSortedView(this.previousFrame, updatedSortedList, updatedUser);
            // Dispose of the home screen
            this.dispose();

        } catch (IOException e) {
            // Catches the IOException thrown by FileRestaurant
            JOptionPane.showMessageDialog(this, "There was an error updating the restaurant's" +
                    "information. Please try again later.");
        }
    }

    @Override
    public void back() {
        // Update the User
        User updatedUser = updateUser();
        // Go back home with the updated user
        // Since the previous screen is home
        this.home(updatedUser);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action Listeners for Back and Home Buttons (Uniform Implementation)
        if (e.getActionCommand().equals("Back")) {
            this.back();
        } else if (e.getActionCommand().equals("Home")) {
            this.home(this.user);
        }
    }
}
