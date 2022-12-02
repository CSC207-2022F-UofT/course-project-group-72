package filtering_use_case.screens;

import entities.Restaurant;
import entities.User;
import filtering_use_case.interfaces.ChoicesInputBoundary;
import filtering_use_case.interactors.sortChoices;
import restaurant_use_case.gateways.RestaurantDSGateway;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectionsActionListener implements ActionListener {
    JFrame frame;
    RestaurantDSGateway choicesGateway;
    User user;
    JTextField query;
    JTextField location;
    JComboBox<String> cuisineType;
    JComboBox<Integer> priceBucket;
    JComboBox<Integer> avgStars;
    ButtonGroup sortButtons;
    ButtonGroup sortDirection;


    public SelectionsActionListener(JFrame frame,
                                    RestaurantDSGateway choicesGateway,
                                    User user,
                                    JTextField query,
                                    JTextField location,
                                    JComboBox<String> cuisineType,
                                    JComboBox<Integer> priceBucket,
                                    JComboBox<Integer> avgStars,
                                    ButtonGroup sortButtons,
                                    ButtonGroup sortDirection) {
        super();
        this.frame = frame;
        this.choicesGateway = choicesGateway;
        this.user = user;
        this.query = query;
        this.location = location;
        this.cuisineType = cuisineType;
        this.priceBucket = priceBucket;
        this.avgStars = avgStars;
        this.sortButtons = sortButtons;
        this.sortDirection = sortDirection;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Initialize Needed Objects
        ChoicesPresenter presenter = new ChoicesResponseFormatter();
        ChoicesInputBoundary interactor = new sortChoices(choicesGateway, presenter);
        ChoicesController choicesController = new ChoicesController(interactor);

        try {
            ChoicesResponseModel selections = choicesController.select(
                    query.getText(),
                    location.getText(),
                    cuisineType.getItemAt(cuisineType.getSelectedIndex()),
                    priceBucket.getItemAt(priceBucket.getSelectedIndex()),
                    avgStars.getItemAt(avgStars.getSelectedIndex()),
                    sortButtons.getSelection().getActionCommand(),
                    sortDirection.getSelection().getActionCommand()
            );

            // Get Sorted List and Call ChoicesSortedView
            ArrayList<Restaurant> sortedList = selections.getRestaurants();


            ChoicesSortedView sortedView = new ChoicesSortedView(sortedList, user);

            // Setup for Calling SortedView and Disposal of This Window
            this.frame.setVisible(false);
            this.frame.dispose();
            sortedView.setVisible(true);
            frame.repaint();

            // Exception
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.frame, ex.toString());

        }
    }
}
