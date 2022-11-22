package filtering_use_case;
import entities.Restaurant;
import entities.RestaurantFactory;
import entities.User;
import restaurant_use_case.*;

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

    // TODO: pass user through login -> home screen -> choices sorted

    public ChoicesSortedView(ArrayList<Restaurant> sortedList) {

        //TODO: organize/format components
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        for (Restaurant restaurant : sortedList) {
            // Components
            restaurantName = new JLabel("Name: " + restaurant.getName());
            restaurantPrice = new JLabel("Price Rating($): " + restaurant.getPriceBucket());
            restaurantLocation = new JLabel("Location: " + restaurant.getLocation());
            restaurantCuisineType = new JLabel("Cuisine: " + restaurant.getCuisineType());
            restaurantAvgStars = new JLabel("Star Rating(/5): " + restaurant.getAvgStars());

            JPanel restaurantPanel = new JPanel();
            restaurantPanel.add(restaurantName);
            restaurantPanel.add(restaurantPrice);
            restaurantPanel.add(restaurantLocation);
            restaurantPanel.add(restaurantCuisineType);

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
