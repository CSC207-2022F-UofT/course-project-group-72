package filtering_use_case;

import entities.Restaurant;
import entities.User;
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

            ArrayList<Restaurant> sortedList = selections.getRestaurants();
            ChoicesSortedView sortedView = new ChoicesSortedView(sortedList, user);

            this.frame.setVisible(false);
            this.frame.dispose();
            sortedView.setVisible(true);
            frame.repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.frame, ex.toString());

        }
    }
}

