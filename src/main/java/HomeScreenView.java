import restaurant_screens.ChoicesController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreenView extends JFrame implements ActionListener {

    // Search Field(s) Temporarily placing Location as an input field
    JTextField searchBox = new JTextField();
    JTextField locationBox = new JTextField();

    // Drop-Down Menus Options
    Integer[] pricingOptions = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Integer[] avgStarsOptions = { 1, 2, 3, 4, 5};

    // TODO: add more cuisine options
    String[] cuisineOptions = { "Food"};


    // Drop-Down Menus
    JComboBox pricingDropDown = new JComboBox(pricingOptions);
    JComboBox avgStartsDropDown = new JComboBox(avgStarsOptions);
    JComboBox cuisineDropDown = new JComboBox(cuisineOptions);

    // Sort Method Buttons
    JRadioButton sortPriceButton = new JRadioButton();

    JRadioButton sortAvgStarsButton = new JRadioButton();

    JRadioButton sortNameButton = new JRadioButton();

    ChoicesController choicesController;

    public HomeScreenView(ChoicesController choicesController) { // should have User current user as argument

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
