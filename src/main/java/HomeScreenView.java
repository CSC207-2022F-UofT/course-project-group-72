import entities.Restaurant;
import restaurant_screens.ChoicesController;
import restaurant_screens.ChoicesSortedView;
import restaurant_use_case.ChoicesRequestModel;
import restaurant_use_case.ChoicesResponseModel;
import restaurant_use_case.sortChoices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class HomeScreenView extends JFrame implements ActionListener {
    /**
     * The search query
     */
    JTextField query;
    /**
     * The input location (Postal Code)
     */
    JTextField location;
    /**
     * The selected price bucket (pricing /10)
     */
    JComboBox<Integer> priceBucket;
    /**
     * The select star rating (/5 stars)
     */
    JComboBox<Integer> avgStars;
    /**
     * The selected cuisine type
     */
    JComboBox<String> cuisineType;
    /**
     * The group of radiobuttons for the sorting selection
     */
    ButtonGroup sortButtons;
    /**
     * The radiobutton for sorting by price
     */
    JRadioButton sortPriceButton;
    /**
     * The radiobutton for sorting by average stars
     */
    JRadioButton sortAvgStarsButton;
    /**
     * The radiobutton for sorting by name (alphabetical or reverse-alphabetical order)
     */
    JRadioButton sortNameButton;
    /**
     * The group of radiobuttons for the sorting direction
     */
    ButtonGroup sortDirection;
    /**
     * The radiobutton for sorting direction
     */
    JRadioButton sortAscending;
    /**
     * The radiobutton for sorting direction
     */
    JRadioButton sortDescending;
    /**
     * The choices controller
     */
    ChoicesController choicesController;

    public HomeScreenView(ChoicesController choicesController) { // should have User object -> current user as argument

        this.choicesController = choicesController;

        // Search Field(s) Temporarily placing Location as an input field
        JPanel searchField = new JPanel();

        query = new JTextField();
        location = new JTextField();

        searchField.add(query);

        // Search Button
        JButton searchButton = new JButton("Search");
        searchField.add(searchButton);
        searchButton.addActionListener(this);

        // Drop-Down Menus Options
        Integer[] pricingOptions = { 1, 2, 3, 4, 5};
        Integer[] avgStarsOptions = { 1, 2, 3, 4, 5};

        // TODO: add more cuisine options
        String[] cuisineOptions = {"Food"};


        // Drop-Down Menus
        priceBucket = new JComboBox<>(pricingOptions);
        avgStars = new JComboBox<>(avgStarsOptions);
        cuisineType = new JComboBox<>(cuisineOptions);

        // Filter Options
        JPanel filterFields = new JPanel();
        filterFields.add(priceBucket);
        filterFields.add(avgStars);
        filterFields.add(cuisineType);

        // Sort Method Buttons
        JPanel sortFields = new JPanel();
        sortButtons = new ButtonGroup();

        // Initialize Buttons
        sortPriceButton = new JRadioButton();
        sortAvgStarsButton = new JRadioButton();
        sortNameButton = new JRadioButton();

        // Add to selection button group
        sortButtons.add(sortPriceButton);
        sortButtons.add(sortAvgStarsButton);
        sortButtons.add(sortNameButton);

        // Sorting Direction Button Group
        sortDirection = new ButtonGroup();

        // Separate Direction Buttons (Sort by Direction, Ex: Ascending, Descending)
        sortAscending = new JRadioButton();
        sortDescending = new JRadioButton();

        // Add to direction button group
        sortDirection.add(sortAscending);
        sortDirection.add(sortDescending);

        // Add individual panels to panel (can't add Button Group)
        sortFields.add(sortPriceButton);
        sortFields.add(sortAvgStarsButton);
        sortFields.add(sortNameButton);
        sortFields.add(sortAscending);
        sortFields.add(sortDescending);

        // Setup Window
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(searchField);
        this.add(filterFields);
        this.add(sortFields);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if (Objects.equals(e.getActionCommand(), "Search")) {
                ChoicesResponseModel selections = choicesController.select(
                        query.getText(),
                        location.getText(),
                        cuisineType.getItemAt(cuisineType.getSelectedIndex()),
                        priceBucket.getItemAt(priceBucket.getSelectedIndex()),
                        avgStars.getItemAt(avgStars.getSelectedIndex()),
                        sortButtons.getSelection().getActionCommand(),
                        sortDirection.getSelection().getActionCommand()
                );

                Container sortedView = new ChoicesSortedView(selections);
                this.setVisible(false);
                sortedView.setVisible(true);

            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void main(String[] args){
        HomeScreenView view = new HomeScreenView(choicesController);
        view.setVisible(true);
    }

}
