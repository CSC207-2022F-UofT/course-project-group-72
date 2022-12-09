package filtering_feature.screens;

import entities.Restaurant;
import entities.User;
import filtering_feature.interactors.*;
import filtering_feature.interfaces.ChoicesInputBoundary;
import restaurant_feature.interfaces.RestaurantDSGateway;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import static filtering_feature.screens.HomeScreenView.*;

public class SelectionsActionListener implements ActionListener {
    /**
     * The JFrame sent through previous view
     */
    JFrame frame;
    /**
     * The gateway used for accessing restaurant database
     */
    RestaurantDSGateway choicesGateway;
    /**
     * The current user
     */
    User user;
    /**
     * The user's input search query for restaurant name
     */
    JTextField query;
    /**
     * The user's input search query for location
     */
    JTextField location;
    /**
     * The user's input selection for cuisine type
     */
    JComboBox<String> cuisineType;
    /**
     * The user's input selection for pricing
     */
    JComboBox<Integer> priceBucket;
    /**
     * The user's input selection for rating
     */
    JComboBox<Integer> avgStars;
    /**
     * The user's input selection for sort method
     */
    ButtonGroup sortButtons;
    /**
     * The user's input selection for sort direction
     */
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

        // Finalize Sorting Method
        String sortInput = sortButtons.getSelection().getActionCommand();

        // Facade usage using Sorting, delegate by user selection from HomeScreenView
        Sorting sortMethod;

        if (Objects.equals(sortInput, AVG_STARS)) {
            sortMethod = new SortAvgStars();
        }

        else if (Objects.equals(sortInput, PRICE)) {
            sortMethod = new SortPrice();
        }

        else {
            sortMethod = new SortName();
        }

        // Initialize
        ChoicesInputBoundary interactor = new SortChoicesInteractor(choicesGateway, presenter, sortMethod);
        ChoicesController choicesController = new ChoicesController(interactor);

        try {
            ChoicesResponseModel selections = choicesController.select(
                    query.getText(),
                    location.getText(),
                    cuisineType.getItemAt(cuisineType.getSelectedIndex()),
                    priceBucket.getItemAt(priceBucket.getSelectedIndex()),
                    avgStars.getItemAt(avgStars.getSelectedIndex()),
                    sortDirection.getSelection().getActionCommand()
            );

            // Get Sorted List and Call ChoicesSortedView
            ArrayList<Restaurant> sortedList = selections.getRestaurants();


            ChoicesSortedView sortedView = new ChoicesSortedView(this.frame, sortedList, user);

            // Setup for Calling SortedView and Disposal of This Window
            this.frame.setVisible(false);
            this.frame.dispose();
            this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            sortedView.setVisible(true);
            frame.repaint();

            // Exception
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.frame, ex.toString());

        }
    }
}
