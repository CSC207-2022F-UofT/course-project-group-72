package restaurant_screens;
import entities.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChoicesSortedView extends JFrame {

    public ChoicesSortedView(ArrayList<Restaurant> sortedList) {


        // Create Window
        JFrame display_list = new JFrame();
        // display_list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //TODO: organize/format components
        for (Restaurant restaurant : sortedList) {
            // Components
            JLabel restarauntName = new JLabel(restaurant.getName());
            JLabel restarauntPrice = new JLabel(String.valueOf(restaurant.getPriceBucket()));
            JLabel restarauntLocation = new JLabel(restaurant.getLocation());
            JLabel restarauntCuisineType = new JLabel(restaurant.getCuisineType());

            JPanel restaurantPanel = new JPanel();
            restaurantPanel.add(restarauntName);
            restaurantPanel.add(restarauntPrice);
            restaurantPanel.add(restarauntLocation);
            restaurantPanel.add(restarauntCuisineType);

            display_list.add(restaurantPanel);

        }

        // display
        display_list.setLocationRelativeTo(null);
        display_list.pack();
        display_list.setVisible(true);

    }

    public static void main(String[] args) {
        // TODO: main call
        //new ChoicesSortedView();
    }

}
