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
     * The choices controller
     */
    ChoicesController choicesController;

    public HomeScreenView(ChoicesController choicesController) {

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
        Integer[] pricingOptions = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
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
        ButtonGroup sortButtons = new ButtonGroup();

        // Initialize Buttons
        JRadioButton sortPriceButton = new JRadioButton();
        JRadioButton sortAvgStarsButton = new JRadioButton();
        JRadioButton sortNameButton = new JRadioButton();

        // Add to button group
        sortButtons.add(sortPriceButton);
        sortButtons.add(sortAvgStarsButton);
        sortButtons.add(sortNameButton);

        // Add individual panels to panel (can't add Button Group)
        sortFields.add(sortPriceButton);
        sortFields.add(sortAvgStarsButton);
        sortFields.add(sortNameButton);

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
                        sortButtons.getSelection().getActionCommand()
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
