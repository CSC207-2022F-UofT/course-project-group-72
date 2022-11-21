package restaurant_screens;
import entities.Restaurant;
import restaurant_use_case.ChoicesResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChoicesSortedView extends JFrame {

    public ChoicesSortedView(ChoicesResponseModel responseModel) {


        // Create Window
        JFrame display_list = new JFrame();
        // display_list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //TODO: organize/format components

        ArrayList<Restaurant> sortedList = responseModel.getRestaurants();

        for (Restaurant restaurant : sortedList) {
            // Components
            JLabel restaurantName = new JLabel(restaurant.getName());
            JLabel restaurantPrice = new JLabel(String.valueOf(restaurant.getPriceBucket()));
            JLabel restaurantLocation = new JLabel(restaurant.getLocation());
            JLabel restaurantCuisineType = new JLabel(restaurant.getCuisineType());

            JPanel restaurantPanel = new JPanel();
            restaurantPanel.add(restaurantName);
            restaurantPanel.add(restaurantPrice);
            restaurantPanel.add(restaurantLocation);
            restaurantPanel.add(restaurantCuisineType);

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
