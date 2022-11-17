import restaurant_screens.ChoicesController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreenView extends JFrame implements ActionListener {

    ChoicesController choicesController;

    public HomeScreenView(ChoicesController choicesController) {

        this.choicesController = choicesController;

        // Search Field(s) Temporarily placing Location as an input field
        JPanel searchField = new JPanel();

        JTextField searchBox = new JTextField();
        JTextField locationBox = new JTextField();

        searchField.add(searchBox);

        // Search Button
        JButton search = new JButton("Search");
        searchField.add(search);
        search.addActionListener(this);



        // Drop-Down Menus Options
        Integer[] pricingOptions = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] avgStarsOptions = { 1, 2, 3, 4, 5};

        // TODO: add more cuisine options
        String[] cuisineOptions = {"Food"};


        // Drop-Down Menus
        JComboBox pricingDropDown = new JComboBox(pricingOptions);
        JComboBox avgStarsDropDown = new JComboBox(avgStarsOptions);
        JComboBox cuisineDropDown = new JComboBox(cuisineOptions);

        // Filter Options
        JPanel filterFields = new JPanel();
        filterFields.add(pricingDropDown);
        filterFields.add(avgStarsDropDown);
        filterFields.add(cuisineDropDown);


        // Sort Method Buttons
        JPanel sortFields = new JPanel();
        JRadioButton sortPriceButton = new JRadioButton();
        JRadioButton sortAvgStarsButton = new JRadioButton();
        JRadioButton sortNameButton = new JRadioButton();

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

    }

    public static void main(String[] args){
//        HomeScreenView view = new HomeScreenView();
//        HomeScreenView.setVisible(true);
    }
}
