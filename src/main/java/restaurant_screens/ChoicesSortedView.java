package restaurant_screens;
import entities.Restaurant;
import entities.RestaurantFactory;
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

    public ChoicesSortedView(ArrayList<Restaurant> sortedList) {
        // format response model

        // Create Window
//        JFrame display_list = new JFrame();
        // display_list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //TODO: organize/format components
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        for (Restaurant restaurant : sortedList) {
            // Components
            restaurantName = new JLabel(restaurant.getName());
            restaurantPrice = new JLabel(String.valueOf(restaurant.getPriceBucket()));
            restaurantLocation = new JLabel(restaurant.getLocation());
            restaurantCuisineType = new JLabel(restaurant.getCuisineType());

            JPanel restaurantPanel = new JPanel();
            restaurantPanel.add(restaurantName);
            restaurantPanel.add(restaurantPrice);
            restaurantPanel.add(restaurantLocation);
            restaurantPanel.add(restaurantCuisineType);

//            display_list.add(restaurantPanel);
            this.add(restaurantPanel);


        }

        // display
//        display_list.setLocationRelativeTo(null);
//        display_list.pack();
//        display_list.setVisible(true);


        // Window options
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 350));
        this.pack();
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        repaint();

    }
}
