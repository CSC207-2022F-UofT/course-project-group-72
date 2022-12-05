package filtering_use_case.screens;
import entities.Restaurant;
import entities.RestaurantFactory;
import entities.User;
//import global.ViewRestaurantActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ChoicesSortedView extends JFrame {
    //TODO: java documentation
    JLabel restaurantName;
    JLabel restaurantPrice;
    JLabel restaurantLocation;
    JLabel restaurantCuisineType;
    JLabel restaurantAvgStars;
    JButton viewRestaurantButton;

    // TODO: pass user through login -> home screen -> choices sorted

    public ChoicesSortedView(ArrayList<Restaurant> sortedList, User user) {

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
            viewRestaurantButton = new JButton();

            // Add all elements to a single JPanel
            JPanel restaurantPanel = new JPanel();
            restaurantPanel.add(restaurantName);
            restaurantPanel.add(restaurantPrice);
            restaurantPanel.add(restaurantLocation);
            restaurantPanel.add(restaurantCuisineType);
            restaurantPanel.add(viewRestaurantButton);

            // Add ViewRestaurantActionListener to viewRestaurantButton
            //viewRestaurantButton.addActionListener(new ViewRestaurantActionListener(user, restaurant));

            this.add(restaurantPanel);


        }

        // Window options
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 550));
        this.pack();
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        repaint();

    }
}