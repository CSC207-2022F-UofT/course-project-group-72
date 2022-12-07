package filtering_use_case.screens;
import entities.Restaurant;
import entities.RestaurantFactory;
import entities.User;
import global.IFrame;
import global.ViewRestaurantActionListener;
import review_use_case.screens.RestaurantView;
//import global.ViewRestaurantActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ChoicesSortedView extends JFrame implements IFrame{
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
        this.pack();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.repaint();

    }

    @Override
    public void refresh() {
        this.dispose();
        new ChoicesSortedView(this.previousFrame, this.sortedList, this.user);
    }

    @Override
    public void back() {
        JFrame frame = this.previousFrame;
        frame.setVisible(true);
        this.dispose();
    }

    @Override
    public void home() {
        try {
            new HomeScreenView(this.user);
            this.dispose();
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.");
        }
    }
}